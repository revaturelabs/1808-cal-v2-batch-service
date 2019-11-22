package com.revaturelabs.caliber.batch;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "trainer_batch")
@Entity
public class BatchAssignment implements Serializable {
  @Column
  private String role;

  @Id
  @JoinColumn(name = "email")
  @ManyToOne
  private Trainer trainer;

  @Id
  @JoinColumn(name = "batch_id")
  @ManyToOne
  @JsonBackReference
  private Batch batch;

  public BatchAssignment() {
  }

  public BatchAssignment(String role, Trainer trainer, Batch batch) {
    this.role = role;
    this.trainer = trainer;
    this.batch = batch;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Trainer getTrainer() {
    return trainer;
  }

  public void setTrainer(Trainer trainer) {
    this.trainer = trainer;
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
    BatchAssignment that = (BatchAssignment) o;
    return Objects.equals(getRole(), that.getRole()) &&
      Objects.equals(getTrainer(), that.getTrainer()) &&
      Objects.equals(getBatch(), that.getBatch());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getRole(), getTrainer(), getBatch());
  }

  @Override
  public String toString() {
    return "BatchAssignment{" +
      "role='" + role + '\'' +
      ", trainer=" + trainer.getEmail() +
      ", batch=" + batch.getId() +
      '}';
  }
}
