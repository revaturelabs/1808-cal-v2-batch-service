package com.revaturelabs.caliber.batch;

import com.revaturelabs.caliber.batch.associate.assignment.AssociateAssignment;
import com.revaturelabs.caliber.batch.employee.assignment.EmployeeAssignment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Table(name = "batch")
@Entity
public class Batch {
  @Id
  @Column(name = "batch_id", unique = true)
  private String id;

  @Column
  @NotNull
  private String name;

  @Column(name = "start_date")
  @NotNull
  private LocalDate startDate;

  @Column(name = "end_date")
  @NotNull
  private LocalDate endDate;

  @Column
  private String skill;

  @Column
  private String location;

  @Column
  private String type;

  @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
  private Set<EmployeeAssignment> employeeAssignments;

  @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
  private Set<AssociateAssignment> associateAssignments;

  public Batch() {
  }

  public Batch(String id, String name, LocalDate startDate, LocalDate endDate, String skill, String location, String type) {
    this.id = id;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.skill = skill;
    this.location = location;
    this.type = type;
  }

  public Batch(String id, String name, LocalDate startDate, LocalDate endDate, String skill, String location, String type, Set<EmployeeAssignment> employeeAssignments) {
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

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
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

  public Set<EmployeeAssignment> getEmployeeAssignments() {
    return employeeAssignments;
  }

  public void setEmployeeAssignments(Set<EmployeeAssignment> employeeAssignments) {
    this.employeeAssignments = employeeAssignments;
  }

  public Set<AssociateAssignment> getAssociateAssignments() {
    return associateAssignments;
  }

  public void setAssociateAssignments(Set<AssociateAssignment> associateAssignments) {
    this.associateAssignments = associateAssignments;
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
    String emp =  employeeAssignments == null ? "[0]" : "[" + Integer.toString(employeeAssignments.size()) + "]";
    String train = associateAssignments == null ? "[0]" : "[" + Integer.toString(associateAssignments.size()) + "]";
    return "Batch{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", startDate=" + startDate +
      ", endDate=" + endDate +
      ", skill='" + skill + '\'' +
      ", location='" + location + '\'' +
      ", type='" + type + '\'' +
      ", employeeAssignments=" + emp +
      ", associateAssignments=" + train +
      '}';
  }
}
