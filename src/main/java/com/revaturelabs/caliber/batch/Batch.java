package com.revaturelabs.caliber.batch;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Table(name = "batch")
@Entity
public class Batch {
  @Id
  @Column(name = "batch_id", unique = true)
  private String id;

  @Column
  private String name;

  @Column(name = "start_date")
  private Instant startDate;

  @Column(name = "end_date")
  private Instant endDate;

  @Column
  private String skill;

  @Column
  private String location;

  @Column
  private String type;

  @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
  private Set<BatchAssignment> employeeAssignments;

  @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
  private Set<TraineeAssignment> traineeAssignments;

  public Batch() {
  }

  public Batch(String id, String name, Instant startDate, Instant endDate, String skill, String location, String type) {
    this.id = id;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.skill = skill;
    this.location = location;
    this.type = type;
  }

  public Batch(String id, String name, Instant startDate, Instant endDate, String skill, String location, String type, Set<BatchAssignment> employeeAssignments) {
    this.id = id;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.skill = skill;
    this.location = location;
    this.type = type;
    this.employeeAssignments = employeeAssignments;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Instant getStartDate() {
    return startDate;
  }

  public void setStartDate(Instant startDate) {
    this.startDate = startDate;
  }

  public Instant getEndDate() {
    return endDate;
  }

  public void setEndDate(Instant endDate) {
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

  public Set<BatchAssignment> getEmployeeAssignments() {
    return employeeAssignments;
  }

  public void setEmployeeAssignments(Set<BatchAssignment> employeeAssignments) {
    this.employeeAssignments = employeeAssignments;
  }

  public Set<TraineeAssignment> getTraineeAssignments() {
    return traineeAssignments;
  }

  public void setTraineeAssignments(Set<TraineeAssignment> traineeAssignments) {
    this.traineeAssignments = traineeAssignments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Batch batch = (Batch) o;
    return Objects.equals(getId(), batch.getId()) &&
      Objects.equals(getName(), batch.getName()) &&
      Objects.equals(getStartDate(), batch.getStartDate()) &&
      Objects.equals(getEndDate(), batch.getEndDate()) &&
      Objects.equals(getSkill(), batch.getSkill()) &&
      Objects.equals(getLocation(), batch.getLocation()) &&
      Objects.equals(getType(), batch.getType());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getStartDate(), getEndDate(), getSkill(), getLocation(), getType());
  }

  @Override
  public String toString() {
    return "Batch{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", startDate=" + startDate +
      ", endDate=" + endDate +
      ", skill='" + skill + '\'' +
      ", location='" + location + '\'' +
      ", type='" + type + '\'' +
      ", employeeAssignments=" + employeeAssignments.size() +
      ", traineeAssignments=" + traineeAssignments.size() +
      '}';
  }
}
