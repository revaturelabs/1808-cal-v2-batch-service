package com.revaturelabs.caliber.batch;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "trainer")
@Entity
public class Trainer {

  @Id
  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
  @JsonBackReference
  private Set<BatchAssignment> trainingBatches;

  public Trainer() {
  }

  public Trainer(String email, String firstName, String lastName) {
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

  public Set<BatchAssignment> getTrainingBatches() {
    return trainingBatches;
  }

  public void setTrainingBatches(Set<BatchAssignment> trainingBatches) {
    this.trainingBatches = trainingBatches;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Trainer trainer = (Trainer) o;
    return Objects.equals(getEmail(), trainer.getEmail()) &&
      Objects.equals(getFirstName(), trainer.getFirstName()) &&
      Objects.equals(getLastName(), trainer.getLastName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getEmail(), getFirstName(), getLastName());
  }

  @Override
  public String toString() {
    return "Trainer{" +
      "email='" + email + '\'' +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", trainingBatches=" + trainingBatches.size() +
      '}';
  }
}
