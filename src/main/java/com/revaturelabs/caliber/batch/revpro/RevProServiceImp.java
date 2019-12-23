package com.revaturelabs.caliber.batch.revpro;

import com.revaturelabs.caliber.batch.Batch;
import com.revaturelabs.caliber.batch.BatchService;
import com.revaturelabs.caliber.batch.EmployeeAssignment;
import com.revaturelabs.caliber.batch.EmployeeAssignmentRepository;
import com.revaturelabs.caliber.batch.employee.Employee;
import com.revaturelabs.caliber.batch.employee.EmployeeService;
import com.revaturelabs.caliber.batch.revpro.dto.BatchDto;
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

  Logger logger = LogManager.getLogger(RevProServiceImp.class);

  @Autowired
  RevProClient revProClient;

  @Autowired
  BatchService batchService;

  @Autowired
  EmployeeService employeeService;

  @Autowired
  EmployeeAssignmentRepository employeeAssignmentRepository;

  public void sync() {
    List<Batch> batches = getCurrentBatches();
    batches.forEach((batch) -> {
      Set<EmployeeAssignment> employees = batch.getEmployeeAssignments();
      batch.setTraineeAssignments(new HashSet<>());
      batch.setEmployeeAssignments(new HashSet<>());
      employees.forEach(employeeAssignment -> employeeService.createOrUpdate(employeeAssignment.getEmployee()));
    });

  }

  private void syncBatch(Batch updatedBatch) {

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

    String token = getToken();

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

  private String getToken() {
    return revProClient.getCreds("{\n" +
      "  \"password\" : \"{password}\",\n" +
      "  \"userName\" : \"{username}\"\n" +
      "}").getData();
  }

  private String applyRevProDateFormat(LocalDate date) {
    return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
  }

}
