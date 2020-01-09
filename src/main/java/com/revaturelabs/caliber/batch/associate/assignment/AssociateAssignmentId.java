package com.revaturelabs.caliber.batch.associate.assignment;

import java.io.Serializable;
import java.util.Objects;

public class AssociateAssignmentId implements Serializable {
  private String batch;
  private String associate;

  public AssociateAssignmentId() {
  }

  public AssociateAssignmentId(String batch, String associate) {
    this.batch = batch;
    this.associate = associate;
  }

  public String getBatch() {
    return batch;
  }

  public void setBatch(String batch) {
    this.batch = batch;
  }

  public String getAssociate() {
    return associate;
  }

  public void setAssociate(String associate) {
    this.associate = associate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AssociateAssignmentId that = (AssociateAssignmentId) o;
    return Objects.equals(getBatch(), that.getBatch()) &&
      Objects.equals(getAssociate(), that.getAssociate());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getBatch(), getAssociate());
  }

  @Override
  public String toString() {
    return "AssociateAssignmentId{" +
      "batch='" + batch + '\'' +
      ", associate='" + associate + '\'' +
      '}';
  }
}
