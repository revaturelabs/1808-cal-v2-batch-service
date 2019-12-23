package com.revaturelabs.caliber.batch;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revaturelabs.caliber.batch.employee.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "employee_batch")
@Entity
public class EmployeeAssignment implements Serializable {
  @Column
  private String role;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @JoinColumn(name = "employee_id")
  @ManyToOne
  private Employee employee;

  @JoinColumn(name = "batch_id")
  @ManyToOne(cascade = CascadeType.ALL)
  @JsonBackReference
  private Batch batch;

  public EmployeeAssignment() {
  }

  public EmployeeAssignment(String role, Employee employee, Batch batch) {
    this.role = role;
    this.employee = employee;
    this.batch = batch;
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
  public String toString() {
    return "EmployeeAssignment{" +
      "role='" + role + '\'' +
      ", employee=" + employee.getEmail() +
      ", batch=" + batch.getId() +
      '}';
  }
}
