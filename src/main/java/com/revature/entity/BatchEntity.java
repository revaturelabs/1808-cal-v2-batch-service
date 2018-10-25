package com.revature.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BatchEntity to represent a batch. It holds the training name,
 * training type, skill type, trainer, co-trainer, location, start date,
 * end date, good grade, and passing grade.
 * 
 * @author Bita, Justin
 *
 */
@Entity
@Table(name="BATCH")
public class BatchEntity {
	@Id
	@Column(name="BATCH_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer batchId;
	
	@Column(name="TRAINING_NAME")
	private String trainingName;
	
	@Column(name="TRAINING_TYPE")
	private String trainingType;
	
	@Column(name="SKILL_TYPE")
	private String skillType;
	
	@Column(name="TRAINER")
	private String trainer;
	
	@Column(name="CO_TRAINER")
	private String coTrainer;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="START_DATE")
	private LocalDate startDate;
	
	@Column(name="END_DATE")
	private LocalDate endDate;
	
	@Column(name="GOOD_GRADE")
	private Integer goodGrade;
	
	@Column(name="PASSING_GRADE")
	private Integer passingGrade;
	
	/**
	 * Constructor for BatchEntity. It holds the training name,
	 * training type, skill type, trainer, co-trainer, location, start date,
	 * end date, good grade, and passing grade.
	 * 
	 * @param trainingName A String to represent the training name
	 * @param trainingType A String to represent the training type
	 * @param skillType A String to represent the skill type.
	 * @param trainer A String to represent the trainer
	 * @param coTrainer A String to represent the co-trainer
	 * @param location A String to represent the location
	 * @param startDate A LocalDate to represent the start date
	 * @param endDate A LocalDate to represent the end date
	 * @param goodGrade A Integer to represent the good grade
	 * @param passingGrade A Integer to represent the passing grade
	 */
	public BatchEntity(String trainingName, String trainingType, String skillType, String trainer, String coTrainer,
			String location, LocalDate startDate, LocalDate endDate, Integer goodGrade, Integer passingGrade) {
		super();
		this.trainingName = trainingName;
		this.trainingType = trainingType;
		this.skillType = skillType;
		this.trainer = trainer;
		this.coTrainer = coTrainer;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.goodGrade = goodGrade;
		this.passingGrade = passingGrade;
	}
	/**
	 * Default constructor of BatchEntity.
	 */
	public BatchEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Getter for training name.
	 * @return A String that holds the batch's training name.
	 */
	public String getTrainingName() {
		return trainingName;
	}
	/**
	 * Setter for training name.
	 * @param trainingName A String that holds the batch's training name.
	 */
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	/**
	 * Getter for training type.
	 * @return A String that holds the batch's training type.
	 */
	public String getTrainingType() {
		return trainingType;
	}
	/**
	 * Setter for training type.
	 * @param trainingType A String that holds the batch's training type.
	 */
	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}
	/**
	 * Getter for skill type.
	 * @return A String that holds the batch's skill type.
	 */
	public String getSkillType() {
		return skillType;
	}
	/**
	 * Setter for skill type.
	 * @param skillType A String that holds the batch's skill type.
	 */
	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}
	/**
	 * Getter for trainer.
	 * @return A String that holds the batch's trainer.
	 */
	public String getTrainer() {
		return trainer;
	}
	/**
	 * Setter for trainer.
	 * @param trainer A String that holds the batch's trainer.
	 */
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	/**
	 * Getter for co-trainer.
	 * @return A String that holds the batch's co-trainer.
	 */
	public String getCoTrainer() {
		return coTrainer;
	}
	/**
	 * Setter for co-trainer.
	 * @param coTrainer A String that holds the batch's co-trainer.
	 */
	public void setCoTrainer(String coTrainer) {
		this.coTrainer = coTrainer;
	}
	/**
	 * Getter for location.
	 * @return A String that holds the batch's location.
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * Setter for location.
	 * @param location A String that holds the batch's location.
	 */ 
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * Getter for start date.
	 * @return A LocalDate that holds the batch's start date.
	 */
	public LocalDate getStartDate() {
		return startDate;
	}
	/**
	 * Setter for start date.
	 * @param startDate A LocalDate that holds the batch's start date.
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	/**
	 * Getter for end date.
	 * @return A LocalDate that holds the batch's end date.
	 */
	public LocalDate getEndDate() {
		return endDate;
	}
	/**
	 * Setter for end date.
	 * @param endDate A LocalDate that holds the batch's end date.
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	/**
	 * Getter for good grade.
	 * @return A LocalDate that holds the batch's good grade.
	 */
	public Integer getGoodGrade() {
		return goodGrade;
	}
	/**
	 * Setter for good date.
	 * @param goodGrade A Integer that holds the batch's good grade.
	 */
	public void setGoodGrade(Integer goodGrade) {
		this.goodGrade = goodGrade;
	}
	/**
	 * Getter for passing grade.
	 * @return A Integer that holds the batch's passing grade.
	 */
	public Integer getPassingGrade() {
		return passingGrade;
	}
	/**
	 * Setter for passing grade.
	 * @param passingGrade A Integer that holds the batch's passing grade.
	 */
	public void setPassingGrade(Integer passingGrade) {
		this.passingGrade = passingGrade;
	}
	/**
	 * Getter for batch id.
	 * @return A Integer that holds the batch's id.
	 */
	public Integer getBatchId() {
		return batchId;
	}
	/**
	 * Setter for batch id.
	 * @param batchId A Integer that holds the batch's id.
	 */
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	
	
	/**
	 * Return a String to represent the BatchEntity.
	 * @return A String to represent the BatchEntity.
	 */
	@Override
	public String toString() {
		return "BatchEntity [batchId=" + batchId + ", trainingName=" + trainingName + ", trainingType=" + trainingType
				+ ", skillType=" + skillType + ", trainer=" + trainer + ", coTrainer=" + coTrainer + ", location="
				+ location + ", startDate=" + startDate + ", endDate=" + endDate + ", goodGrade=" + goodGrade
				+ ", passingGrade=" + passingGrade + "]";
	}
	
	
	
	

}
