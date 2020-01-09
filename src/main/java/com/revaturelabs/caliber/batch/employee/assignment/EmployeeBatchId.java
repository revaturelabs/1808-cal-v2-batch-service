package com.revaturelabs.caliber.batch.employee.assignment;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeBatchId implements Serializable {
  private String employee;
  private String batch;

  public EmployeeBatchId() {
  }

  public EmployeeBatchId(String employee, String batch) {
    this.employee = employee;
    this.batch = batch;
  }

  public String getEmployee_id() {
    return employee;
  }

  public void setEmployee_id(String employee) {
    this.employee = employee;
  }

  public String getBatch_id() {
    return batch;
  }

  public void setBatch_id(String batch) {
    this.batch = batch;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EmployeeBatchId that = (EmployeeBatchId) o;
    return Objects.equals(getEmployee_id(), that.getEmployee_id()) &&
      Objects.equals(getBatch_id(), that.getBatch_id());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getEmployee_id(), getBatch_id());
  }

  @Override
  public String toString() {
    return "EmployeeBatchId{" +
      "employee='" + employee + '\'' +
      ", batch='" + batch + '\'' +
      '}';
  }
}
