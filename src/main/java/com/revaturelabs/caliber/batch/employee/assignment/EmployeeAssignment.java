package com.revaturelabs.caliber.batch.employee.assignment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revaturelabs.caliber.batch.Batch;
import com.revaturelabs.caliber.batch.employee.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "employee_batch")
@Entity
@IdClass(EmployeeBatchId.class)
public class EmployeeAssignment implements Serializable {
  @Column
  private String role;

  @Id
  @JoinColumn(name = "employee_id")
  @ManyToOne
  private Employee employee;

  @Id
  @JoinColumn(name = "batch_id")
  @ManyToOne
  @JsonBackReference
  private Batch batch;

  public EmployeeAssignment() {
  }

  public EmployeeAssignment(String role, Employee employee, Batch batch) {
    this.role = role;
    this.employee = employee;
    this.batch = batch;
  }

  @JsonIgnore
  public EmployeeBatchId getId() {
    return new EmployeeBatchId(employee.getEmail(), batch.getId());
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Batch getBatch() {
    return batch;
  }

  public void setBatch(Batch batch) {
    this.batch = batch;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EmployeeAssignment that = (EmployeeAssignment) o;
    return Objects.equals(getRole(), that.getRole()) &&
      Objects.equals(getEmployee(), that.getEmployee()) &&
      Objects.equals(getBatch(), that.getBatch());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getRole(), getEmployee(), getBatch());
  }

  @Override
  public String toString() {
    return "EmployeeAssignment{" +
      "role='" + role + '\'' +
      ", employee=" + employee.getEmail() +
      ", batch=" + batch.getId() +
      '}';
  }
}
