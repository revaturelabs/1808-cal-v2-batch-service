package com.revaturelabs.caliber.batch.revpro;

import com.revaturelabs.caliber.batch.*;
import com.revaturelabs.caliber.batch.associate.AssociateService;
import com.revaturelabs.caliber.batch.associate.assignment.AssociateAssignment;
import com.revaturelabs.caliber.batch.associate.assignment.AssociateAssignmentRepository;
import com.revaturelabs.caliber.batch.employee.Employee;
import com.revaturelabs.caliber.batch.employee.assignment.EmployeeAssignment;
import com.revaturelabs.caliber.batch.employee.assignment.EmployeeAssignmentRepository;
import com.revaturelabs.caliber.batch.employee.EmployeeService;
import com.revaturelabs.caliber.batch.revpro.config.RevProConfig;
import com.revaturelabs.caliber.batch.revpro.dto.BatchDto;
import com.revaturelabs.caliber.batch.revpro.dto.RevProResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.*;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
public class RevProServiceImp implements RevProService {

  private Logger logger = LogManager.getLogger(RevProServiceImp.class);

  private String token;

  @Autowired
  private RevProClient revProClient;

  @Autowired
  private BatchService batchService;

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private RevProConfig revProConfig;

  @Autowired
  private AssociateService associateService;

  @Autowired
  EmployeeAssignmentRepository employeeAssignmentRepository;

  @Autowired
  AssociateAssignmentRepository associateAssignmentRepository;

  public void sync() {
    List<Batch> batches = getCurrentBatches();
    batches.forEach(this::syncBatch);
  }

  private void syncBatch(Batch batch) {
    Optional<Batch> storedBatch = batchService.getById(batch.getId());
    Set<EmployeeAssignment> employees = batch.getEmployeeAssignments();

    if (!storedBatch.isPresent() || !storedBatch.get().equals(batch)) {
      batch.setEmployeeAssignments(new HashSet<>());
      batch.setAssociateAssignments(new HashSet<>());
      batchService.create(batch);

      for (EmployeeAssignment employeeAssignment: employees) {
        syncEmployee(employeeAssignment.getEmployee());
        syncEmployeeAssignments(employeeAssignment);
      }
    }

    // Request associates and create the set
    Set<AssociateAssignment> associateAssignments = getAssociatesForBatch(batch);
    Set<AssociateAssignment> invalidRecords = new HashSet<>();

    for (AssociateAssignment associateAssignment : associateAssignments) {
      logger.debug("Trainee {}", associateAssignment.getAssociate());
      try {
        associateService.create(associateAssignment.getAssociate());
      } catch (Exception e) {
        invalidRecords.add(associateAssignment);
        logger.debug("BIG YIKES: {} {}", e, associateAssignment.getAssociate());
      }
    }
    associateAssignments.removeAll(invalidRecords);
    associateAssignmentRepository.saveAll(associateAssignments);

    logger.debug("associateAssignments {}", associateAssignments);
  }

  private void syncEmployeeAssignments(EmployeeAssignment employeeAssignment) {
    Optional<EmployeeAssignment> storedEmployeeAssignment = employeeAssignmentRepository.findById(employeeAssignment.getId());
    if (!storedEmployeeAssignment.isPresent() || !storedEmployeeAssignment.get().equals(employeeAssignment)) {
      try {
        employeeAssignmentRepository.save(employeeAssignment);
      } catch (Exception e) {
        logger.warn("Error persisting employee assignment to batch {} error: {}", kv("employeeAssignment", employeeAssignment), kv("error", e));
      }
    }
  }

  private void syncEmployee(Employee employee) {
    Optional<Employee> storedEmpl = employeeService.getById(employee.getEmail());
    if (!storedEmpl.isPresent()) {
      employeeService.create(new Employee(employee.getEmail(), employee.getFirstName(), employee.getLastName()));
    } else if (!storedEmpl.get().equals(employee)) {
      employee.setTrainingBatches(new HashSet<>());
      employeeService.createOrUpdate(employee);
    }
  }


  private List<Batch> getCurrentBatches() {
    // Sync is based on distance in past
    LocalDate today = LocalDate.now();

    TemporalAmount fifteenWeeks = Period.ofWeeks(20);
    TemporalAmount threeDays = Period.ofDays(3);

    LocalDate oldestStartDate = today.minus(fifteenWeeks);
    LocalDate newestStartDate = today.plus(threeDays);
    LocalDate newestEndDate = today.minus(threeDays);

    String startDateAfter = applyRevProDateFormat(oldestStartDate);
    String startDateBefore = applyRevProDateFormat(newestStartDate);
    String endDateAfter = applyRevProDateFormat(newestEndDate);

    DateTimeFormatter basicDateFormat = DateTimeFormatter.ISO_LOCAL_DATE;
    logger.debug("Dates:\n {}\n {}\n {}\n",
      kv("startDateAfter", startDateAfter),
      kv("startDateBefore", startDateBefore),
      kv("endDateAfter", endDateAfter));

    token = getToken();

    List<Batch> batches = new LinkedList<Batch>();
    revProClient.getBatchInTimeRange(
      startDateAfter,
      startDateBefore,
      endDateAfter,
      null,
      token
    ).getData().forEach((BatchDto b) -> {
      batches.add(b.convertToBatch());
    });

    return batches;
  }

  private Set<AssociateAssignment> getAssociatesForBatch(Batch batch) {
    RevProResponse<BatchDto> batchOfAssociates = revProClient.getAssociatesByBatchId(batch.getId(), token);
    logger.debug("associates {}", batchOfAssociates);
    Set<AssociateAssignment> associates = new LinkedHashSet<>();

    if (batchOfAssociates.getData().getBatchTrainees() != null) {
      batchOfAssociates.getData().getBatchTrainees().forEach((associateDto) -> {
        associates.add(new AssociateAssignment(associateDto.getTrainingStatus(), associateDto.convertToAssociate(),batch));
        logger.debug("Right after add {}", associates);
      });
    }

    logger.debug("This is the associates {}", associates);

    return associates;
  }

  private String getToken() {
    return revProClient.getCreds("{\n" +
      "  \"password\" : \""+revProConfig.getGetRevProPassword()+"\",\n" +
      "  \"userName\" : \""+revProConfig.getRevProUsername()+"\"\n" +
      "}").getData();
  }

  private String applyRevProDateFormat(LocalDate date) {
    return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
  }

}
