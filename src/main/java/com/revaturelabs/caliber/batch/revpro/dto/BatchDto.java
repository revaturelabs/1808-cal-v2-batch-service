package com.revaturelabs.caliber.batch.revpro.dto;

import com.revaturelabs.caliber.batch.Batch;
import com.revaturelabs.caliber.batch.employee.assignment.EmployeeAssignment;
import com.revaturelabs.caliber.batch.associate.assignment.AssociateAssignment;
import com.revaturelabs.caliber.batch.employee.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BatchDto {
  private String salesforceId;
  private String name;
  private String startDate;
  private String endDate;
  private String skill;
  private String location;
  private String type;
  private TrainerDto trainer;
  private List<TrainerDto> coTrainers;
  private List<AssociateDto> batchTrainees;

  public BatchDto(String salesforceId, String name, String startDate, String endDate, String skill, String location, String type, TrainerDto trainer, List<TrainerDto> coTrainers, List<AssociateDto> batchTrainees) {
    this.salesforceId = salesforceId;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.skill = skill;
    this.location = location;
    this.type = type;
    this.trainer = trainer;
    this.coTrainers = coTrainers;
    this.batchTrainees = batchTrainees;
  }

  public BatchDto() {
  }

  public String getSalesforceId() {
    return salesforceId;
  }

  public void setSalesforceId(String salesforceId) {
    this.salesforceId = salesforceId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getSkill() {
    return skill;
  }

  public void setSkill(String skill) {
    this.skill = skill;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public TrainerDto getTrainer() {
    return trainer;
  }

  public void setTrainer(TrainerDto trainer) {
    this.trainer = trainer;
  }

  public List<TrainerDto> getCoTrainers() {
    return coTrainers;
  }

  public void setCoTrainers(List<TrainerDto> coTrainers) {
    this.coTrainers = coTrainers;
  }

  public List<AssociateDto> getBatchTrainees() {
    return batchTrainees;
  }

  public void setBatchTrainees(List<AssociateDto> batchTrainees) {
    this.batchTrainees = batchTrainees;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BatchDto batchDto = (BatchDto) o;
    return Objects.equals(getSalesforceId(), batchDto.getSalesforceId()) &&
      Objects.equals(getName(), batchDto.getName()) &&
      Objects.equals(getStartDate(), batchDto.getStartDate()) &&
      Objects.equals(getEndDate(), batchDto.getEndDate()) &&
      Objects.equals(getSkill(), batchDto.getSkill()) &&
      Objects.equals(getLocation(), batchDto.getLocation()) &&
      Objects.equals(getType(), batchDto.getType()) &&
      Objects.equals(getTrainer(), batchDto.getTrainer()) &&
      Objects.equals(getCoTrainers(), batchDto.getCoTrainers()) &&
      Objects.equals(getBatchTrainees(), batchDto.getBatchTrainees());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getSalesforceId(), getName(), getStartDate(), getEndDate(), getSkill(), getLocation(), getType(), getTrainer(), getCoTrainers(), getBatchTrainees());
  }

  @Override
  public String toString() {
    return "BatchDto{" +
      "salesforceId='" + salesforceId + '\'' +
      ", name='" + name + '\'' +
      ", startDate=" + startDate +
      ", endDate=" + endDate +
      ", skill='" + skill + '\'' +
      ", location='" + location + '\'' +
      ", type='" + type + '\'' +
      ", trainer=" + trainer +
      ", coTrainers=" + coTrainers +
      ", batchTrainees=" + batchTrainees +
      '}';
  }

  public Batch convertToBatch() {
    Batch convertedBatch = new Batch();
    convertedBatch.setId(salesforceId);
    convertedBatch.setName(name);
    convertedBatch.setSkill(skill);
    convertedBatch.setLocation(location);
    convertedBatch.setType(type);

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
    convertedBatch.setStartDate(LocalDate.parse(startDate, dateTimeFormatter));
    convertedBatch.setEndDate(LocalDate.parse(endDate, dateTimeFormatter));

    Employee e = trainer.convertToEmployee();

    Set<EmployeeAssignment> trainingAssignments = new LinkedHashSet<EmployeeAssignment>();

    for (TrainerDto coTrainer: coTrainers) {
      trainingAssignments.add(
        new EmployeeAssignment("cotrainer", coTrainer.convertToEmployee(), convertedBatch)
      );
    }

    EmployeeAssignment employeeAssignment = new EmployeeAssignment("trainer", e, convertedBatch);
    trainingAssignments.add(employeeAssignment);

    convertedBatch.setEmployeeAssignments(trainingAssignments);
    e.setTrainingBatches(trainingAssignments);

    if(batchTrainees != null) {
      Set<AssociateAssignment> associatesInBatch = new LinkedHashSet<>();
      for (AssociateDto associate: batchTrainees) {
        associatesInBatch.add(new AssociateAssignment(associate.getTrainingStatus(), associate.convertToAssociate(), convertedBatch));
      }
      convertedBatch.setAssociateAssignments(associatesInBatch);
    }

    return convertedBatch;
  }
}
