package com.revature.batchservice.entity;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "BATCH_ID_SEQUENCE")
	@SequenceGenerator(name = "BATCH_ID_SEQUENCE", sequenceName = "BATCH_ID_SEQUENCE")
	private int batchId;
	
	@NotNull
	@Column(name="TRAINING_NAME")
	private String trainingName;
	
	@NotNull
	@Column(name="TRAINING_TYPE")
	private String trainingType;
	
	@NotNull
	@Column(name="SKILL_TYPE")
	private String skillType;
	
	@NotNull
	@Column(name="TRAINER")
	private String trainer;
	
	@Column(name="CO_TRAINER")
	private String coTrainer;
	
	@NotNull
	@Column(name="LOCATION_ID")
	private int locationId;
	
	
	private String location;
	
	@NotNull
	@Column(name="START_DATE")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date startDate;
	
	@NotNull
	@Column(name="END_DATE")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date endDate;
	
	@NotNull
	@Column(name="GOOD_GRADE")
	private int goodGrade;
	
	@NotNull
	@Column(name="PASSING_GRADE")
	private int passingGrade;
	
	@NotNull
	@Column(name="NUMBER_OF_WEEKS")
	private int weeks;
	
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
	 * @param startDate A Date to represent the start date
	 * @param endDate A Date to represent the end date
	 * @param goodGrade A int to represent the good grade
	 * @param passingGrade A int to represent the passing grade
	 * @param weeks an int to represent the number of weeks
	 */
	
	public BatchEntity(String trainingName, String trainingType, String skillType, String trainer,
			String coTrainer, int locationId, Date startDate, Date endDate, int goodGrade,
			int passingGrade, int weeks) {
		super();
		this.trainingName = trainingName;
		this.trainingType = trainingType;
		this.skillType = skillType;
		this.trainer = trainer;
		this.coTrainer = coTrainer;
		this.locationId = locationId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.goodGrade = goodGrade;
		this.passingGrade = passingGrade;
		this.weeks = weeks;
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
	 * Getter for start date.
	 * @return A Date that holds the batch's start date.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Setter for start date.
	 * @param startDate A Date that holds the batch's start date.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Getter for end date.
	 * @return A Date that holds the batch's end date.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * Setter for end date.
	 * @param endDate A Date that holds the batch's end date.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * Getter for good grade.
	 * @return A Date that holds the batch's good grade.
	 */
	public int getGoodGrade() {
		return goodGrade;
	}
	/**
	 * Setter for good date.
	 * @param goodGrade A int that holds the batch's good grade.
	 */
	public void setGoodGrade(int goodGrade) {
		this.goodGrade = goodGrade;
	}
	/**
	 * Getter for passing grade.
	 * @return A int that holds the batch's passing grade.
	 */
	public int getPassingGrade() {
		return passingGrade;
	}
	/**
	 * Setter for passing grade.
	 * @param passingGrade A int that holds the batch's passing grade.
	 */
	public void setPassingGrade(int passingGrade) {
		this.passingGrade = passingGrade;
	}
	/**
	 * Getter for batch id.
	 * @return A int that holds the batch's id.
	 */
	public int getBatchId() {
		return batchId;
	}
	/**
	 * Setter for batch id.
	 * @param batchId A int that holds the batch's id.
	 */
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public int getWeeks() {
		return weeks;
	}

	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}

	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BatchEntity other = (BatchEntity) obj;
		if (batchId != other.batchId)
			return false;
		if (coTrainer == null) {
			if (other.coTrainer != null)
				return false;
		} else if (!coTrainer.equals(other.coTrainer))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (goodGrade != other.goodGrade)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (locationId != other.locationId)
			return false;
		if (passingGrade != other.passingGrade)
			return false;
		if (skillType == null) {
			if (other.skillType != null)
				return false;
		} else if (!skillType.equals(other.skillType))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (trainer == null) {
			if (other.trainer != null)
				return false;
		} else if (!trainer.equals(other.trainer))
			return false;
		if (trainingName == null) {
			if (other.trainingName != null)
				return false;
		} else if (!trainingName.equals(other.trainingName))
			return false;
		if (trainingType == null) {
			if (other.trainingType != null)
				return false;
		} else if (!trainingType.equals(other.trainingType))
			return false;
		if (weeks != other.weeks)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batchId;
		result = prime * result + ((coTrainer == null) ? 0 : coTrainer.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + goodGrade;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + locationId;
		result = prime * result + passingGrade;
		result = prime * result + ((skillType == null) ? 0 : skillType.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((trainer == null) ? 0 : trainer.hashCode());
		result = prime * result + ((trainingName == null) ? 0 : trainingName.hashCode());
		result = prime * result + ((trainingType == null) ? 0 : trainingType.hashCode());
		result = prime * result + weeks;
		return result;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", BatchEntity.class.getSimpleName() + "[", "]")
						.add("batchId=" + batchId)
						.add("trainingName='" + trainingName + "'")
						.add("trainingType='" + trainingType + "'")
						.add("skillType='" + skillType + "'")
						.add("trainer='" + trainer + "'")
						.add("coTrainer='" + coTrainer + "'")
						.add("locationId=" + locationId)
						.add("location='" + location + "'")
						.add("startDate=" + startDate)
						.add("endDate=" + endDate)
						.add("goodGrade=" + goodGrade)
						.add("passingGrade=" + passingGrade)
						.add("weeks=" + weeks)
						.toString();
	}
}
