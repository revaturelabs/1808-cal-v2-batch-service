package com.revaturelabs.caliber.batch.revpro.dto;

import com.revaturelabs.caliber.batch.employee.Employee;

import java.util.Objects;

public class TrainerDto {
  private String email;
  private String firstName;
  private String lastName;

  public TrainerDto() {
  }

  public TrainerDto(String email, String firstName, String lastName) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TrainerDto that = (TrainerDto) o;
    return Objects.equals(getEmail(), that.getEmail()) &&
      Objects.equals(getFirstName(), that.getFirstName()) &&
      Objects.equals(getLastName(), that.getLastName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getEmail(), getFirstName(), getLastName());
  }

  @Override
  public String toString() {
    return "TrainerDto{" +
      "email='" + email + '\'' +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      '}';
  }

  public Employee convertToEmployee() {
    Employee convertedEmp = new Employee();
    convertedEmp.setEmail(email);
    convertedEmp.setFirstName(firstName);
    convertedEmp.setLastName(lastName);
    return convertedEmp;
  }

}
