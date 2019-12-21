package com.revaturelabs.caliber.batch.associate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revaturelabs.caliber.batch.TraineeAssignment;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "associate")
public class Associate {

  @Id
  @Column(name = "email")
  @Email
  private String email;

  @Column(name = "salesforce_id", unique = true)
  private String salesforceId;

  @Column(name = "first_name")
  @NotNull
  private String firstName;

  @Column(name = "last_name")
  @NotNull
  private String lastName;

  @OneToMany(mappedBy = "associate", cascade = CascadeType.ALL)
  @JsonBackReference
  private Set<TraineeAssignment> trainingAssignments;

  public Associate() {
  }

  public Associate(String salesforceId, String firstName, String lastName, String email, Set<TraineeAssignment> trainingAssignments) {
    this.salesforceId = salesforceId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.trainingAssignments = trainingAssignments;
  }

  public String getSalesforceId() {
    return salesforceId;
  }

  public void setSalesforceId(String salesforceId) {
    this.salesforceId = salesforceId;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<TraineeAssignment> getTrainingAssignments() {
    return trainingAssignments;
  }

  public void setTrainingAssignments(Set<TraineeAssignment> trainingAssignments) {
    this.trainingAssignments = trainingAssignments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Associate associate = (Associate) o;
    return Objects.equals(getSalesforceId(), associate.getSalesforceId()) &&
      Objects.equals(getFirstName(), associate.getFirstName()) &&
      Objects.equals(getLastName(), associate.getLastName()) &&
      Objects.equals(getEmail(), associate.getEmail());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getSalesforceId(), getFirstName(), getLastName(), getEmail());
  }

  @Override
  public String toString() {
    return "Associate{" +
      "salesforceId='" + salesforceId + '\'' +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", email='" + email + '\'' +
      ", trainingAssignments=" + trainingAssignments.size() +
      '}';
  }
}
