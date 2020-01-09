package com.revaturelabs.caliber.batch.associate.assignment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revaturelabs.caliber.batch.Batch;
import com.revaturelabs.caliber.batch.associate.Associate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "associate_batch")
@IdClass(AssociateAssignmentId.class)
public class AssociateAssignment implements Serializable {
  @Column
  private String trainingStatus;

  @Id
  @JoinColumn(name = "email")
  @ManyToOne
  private Associate associate;

  @Id
  @JoinColumn(name = "batch_id")
  @ManyToOne
  @JsonBackReference
  private Batch batch;

  public AssociateAssignment() {
  }

  public AssociateAssignment(String trainingStatus, Associate associate, Batch batch) {
    this.trainingStatus = trainingStatus;
    this.associate = associate;
    this.batch = batch;
  }

  public String getTrainingStatus() {
    return trainingStatus;
  }

  public void setTrainingStatus(String trainingStatus) {
    this.trainingStatus = trainingStatus;
  }

  public Associate getAssociate() {
    return associate;
  }

  public void setAssociate(Associate associate) {
    this.associate = associate;
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
    AssociateAssignment that = (AssociateAssignment) o;
    return Objects.equals(getTrainingStatus(), that.getTrainingStatus()) &&
      Objects.equals(getAssociate(), that.getAssociate()) &&
      Objects.equals(getBatch(), that.getBatch());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTrainingStatus(), getAssociate(), getBatch());
  }

  @Override
  public String toString() {
    return "AssociateAssignment{" +
      "trainingStatus='" + trainingStatus + '\'' +
      ", associate=" + associate.getEmail() +
      ", batch=" + batch.getId() +
      '}';
  }
}
