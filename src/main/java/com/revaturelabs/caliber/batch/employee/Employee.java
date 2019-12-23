package com.revaturelabs.caliber.batch.employee;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revaturelabs.caliber.batch.EmployeeAssignment;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Table(name = "employee")
@Entity
public class Employee {

  @Id
  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "first_name")
  @NotNull
  private String firstName;

  @Column(name = "last_name")
  @NotNull
  private String lastName;

  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
  @JsonBackReference
  private Set<EmployeeAssignment> trainingBatches;

  public Employee() {
  }

  public Employee(String email, String firstName, String lastName) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Set<EmployeeAssignment> getTrainingBatches() {
    return trainingBatches;
  }

  public void setTrainingBatches(Set<EmployeeAssignment> trainingBatches) {
    this.trainingBatches = trainingBatches;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return Objects.equals(getEmail(), employee.getEmail()) &&
      Objects.equals(getFirstName(), employee.getFirstName()) &&
      Objects.equals(getLastName(), employee.getLastName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getEmail(), getFirstName(), getLastName());
  }

  @Override
  public String toString() {
    String batches = trainingBatches == null ? "[0]" : "[" + trainingBatches.size() + "]";
    return "Employee{" +
      "email='" + email + '\'' +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", trainingBatches=" + trainingBatches +
      '}';
  }
}
