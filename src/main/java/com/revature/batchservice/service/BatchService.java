package com.revature.batchservice.service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.batchservice.entity.BatchEntity;
import com.revature.batchservice.feign.LocationClient;
import com.revature.batchservice.feign.QualityAuditClient;
import com.revature.batchservice.repository.BatchRepository;

import feign.RetryableException;

/**
 * Service class for handling Batches. Has methods for adding a batch to the
 * database, finding all Batches, finding batch by id, finding batch by year,
 * updating, and deleting a batch.
 * 
 * Uses a BatchRepository to make calls to the database.
 * 
 * @author Justin Tu, Bita Mahbod, Daniel Mitre
 *
 */
@Service
public class BatchService implements BatchServiceInterface {

	Logger log = Logger.getLogger("BatchService.class");

	@Autowired
	private BatchRepository br;

	@Autowired
	private LocationClient locationClient;

	/**
	 * Returns a List of all BatchEntities on the connected database.
	 * 
	 * @return a List<BatchEntity> that contains all BatchEntities in the database.
	 */
	@Override
	public List<BatchEntity> findAllBatches() {
		List<BatchEntity> beList = br.findAll();
		OUTER: for (int i = 0; i < beList.size(); i++) {
			BatchEntity be = beList.get(i);
			if (!contactLocationService(be)) {
				for (int j = i; j < beList.size(); j++) {
					beList.get(j).setLocation("Connection to Location database lost");
				}
				break OUTER;
			}
		}
		return beList;
	}

	/**
	 * Returns a List of all BatchEntities that start in a given year, on the
	 * connected database.
	 * 
	 * @param year
	 *            An Integer representing the year to get batches from.
	 * @return A List<BatchEntity> of batches that start in a given year.
	 */
	public List<BatchEntity> findBatchesByYear(Integer startYear) {
		List<BatchEntity> beList = br.findAllBatchesByYear(startYear);
		OUTER: for (int i = 0; i < beList.size(); i++) {
			BatchEntity be = beList.get(i);
			if (!contactLocationService(be)) {
				for (int j = i; j < beList.size(); j++) {
					beList.get(j).setLocation("Connection to Location database lost");
				}
				break OUTER;
			}
		}
		return beList;
	}

	/**
	 * Returns a BatchEntity which has the same id as the given id.
	 * 
	 * @param id
	 *            An Integer that contains the BatchEntity id to look for.
	 * @return a BatchEntity which has the same id as the given id. Null if no
	 *         matching id was found.
	 */
	@Override
	public BatchEntity findBatchById(Integer id) {
		BatchEntity be = br.findOne(id);
		contactLocationService(be);
		return be;
	}

	/**
	 * This method returns a list of the current batches; which means the current
	 * date is between their start date and end date.
	 * 
	 * @return A List<BatchEntity> contains current batches.
	 */
	@Override
	public List<BatchEntity> findCurrentBatches() {
		List<BatchEntity> beList = br.findAllCurrentBatches();
		OUTER: for (int i = 0; i < beList.size(); i++) {
			BatchEntity be = beList.get(i);
			if (!contactLocationService(be)) {
				for (int j = i; j < beList.size(); j++) {
					beList.get(j).setLocation("Connection to Location database lost");
				}
				break OUTER;
			}
		}
		return beList;

	}

	/**
	 * This method returns a list of the start years that the batches are in. The
	 * list ordered in ascending order.
	 * 
	 * @return A List<Integer> contains start years of all batches, in ascending
	 *         order.
	 */
	@Override
	public List<Integer> findBatchYears() {
		List<Integer> years = br.findBatchYears();
		Integer lastYear = br.findLastYear();
		if (!years.contains(lastYear)) {
			years.add(lastYear);
		}
		Collections.reverse(years);
		return years;
	}

	/**
	 * Attempts to add a BatchEntity to the database. Will throw an
	 * IllegalArgumentException if a field in the given BatchEntity is null, (Note:
	 * BatchEntity.coTrainer can be null) if the BatchEntity.passingGrade >
	 * BatchEntity.goodGrade, and if BatchEntity.endDate is before
	 * BatchEntity.startDate.
	 * 
	 * @param be
	 *            The BatchEntity to add to the database.
	 * @throws IllegalArgumentException
	 *             Thrown if a field in be is null (except coTrainer), if the
	 *             passingGrade is greater than the goodGrade, or if the endDate
	 *             comes before the startDate.
	 */
	@Override
	public BatchEntity createBatch(BatchEntity be) throws IllegalArgumentException {
		// Check if a field was null. Co-Trainer can be null
		if (be.getTrainingName() == null) {
			throw new IllegalArgumentException("trainingName was null.");
		}
		if (be.getTrainingType() == null) {
			throw new IllegalArgumentException("trainingType was null.");
		}
		if (be.getLocationId() == null) {
			throw new IllegalArgumentException("locationId was null.");
		}
		if (be.getSkillType() == null) {
			throw new IllegalArgumentException("skillType was null.");
		}
		if (be.getTrainer() == null) {
			throw new IllegalArgumentException("trainer was null.");
		}
		if (be.getPassingGrade() == null) {
			throw new IllegalArgumentException("passingGrade was null.");
		}
		if (be.getGoodGrade() == null) {
			throw new IllegalArgumentException("goodGrade was null.");
		}
		if (be.getStartDate() == null) {
			throw new IllegalArgumentException("startDate was null.");
		}
		if (be.getEndDate() == null) {
			throw new IllegalArgumentException("endDate was null.");
		}

		// Validate field values
		if (be.getPassingGrade() > be.getGoodGrade()) {
			throw new IllegalArgumentException("Passing Grade can not be greater than Good Grade.");
		}
		if (be.getEndDate().compareTo(be.getStartDate()) < 0) {
			throw new IllegalArgumentException("End Date must be After Start date.");
		}
		return br.save(be);
	}

	/**
	 * Takes in a BatchEntity and updates any matching BatchEntity in the database.
	 * If no BatchEntity on the database has a matching id, then the given
	 * BatchEntity is added to the database.
	 * 
	 * @param be The BatchEntity to update.
	 */
	@Override
	public void updateBatch(BatchEntity be) {
		br.save(be);
	}

	/**
	 * Takes in a BatchEntity and attempts to delete it from the database. If the
	 * give BatchEntity does not exist in the database, the database will not be
	 * changed.
	 * 
	 * @param be The BatchEntity to delete from the database.
	 */
	@Override
	public void deleteBatch(Integer batchId) {
		br.delete(batchId);
	}

	/**
	 * Helper method for getting location info from the Location Micro-Service and
	 * putting that info into a BatchEntity.
	 * 
	 * @param be
	 *            The BatchEntity to set location info.
	 * @return Returns true if RetryableException was not thrown by LocaitonClient.
	 *         Returns false otherwise.
	 */
	private boolean contactLocationService(BatchEntity be) {
		try {
			ResponseEntity<String> response = locationClient.getLocationById(be.getLocationId());
			if (response != null && response.hasBody()) {
				String body = response.getBody();
				body = body.substring(body.indexOf(",") + 2);
				be.setLocation(body);
			} else {
				be.setLocation("Location was not found");
			}
			return true;
		} catch (Exception e) {
			log.warn("Could not connect with LocationService");
			log.warn(e.getMessage());
			be.setLocation("Connection to Location database lost");
			return false;
		}
	}

	@Override
	public List<BatchEntity> findBatchesByYearAndQuarter(Integer year, Integer quarter) {
		if(quarter < 1 || quarter > 4) return null;
		List<BatchEntity> beList = br.findAll();
		ListIterator<BatchEntity> iter = beList.listIterator();
		while(iter.hasNext()) {
			BatchEntity be = iter.next();
			if(!isWithinRange(be.getStartDate(), be.getEndDate(), year, quarter)) {
				iter.remove();
			} else {
				contactLocationService(be);
			}
		}
		
		return beList;
	}
	
	private boolean isWithinRange(Date bsDate, Date beDate, Integer year, Integer quarter) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String dateInString = "";
		Date startDate = new Date();
		Date endDate = new Date();
		
		try {
			if(quarter == 1) {
				dateInString = "01-01-" + year;
				startDate = sdf.parse(dateInString);
				dateInString = "31-03-" + year;
				endDate = sdf.parse(dateInString);
			} else if(quarter == 2) {
				dateInString = "01-04-" + year;
				startDate = sdf.parse(dateInString);
				dateInString = "30-06-" + year;
				endDate = sdf.parse(dateInString);
			} else if(quarter == 3) {
				dateInString = "01-07-" + year;
				startDate = sdf.parse(dateInString);
				dateInString = "30-09-" + year;
				endDate = sdf.parse(dateInString);
			} else if(quarter == 4) {
				dateInString = "01-10-" + year;
				startDate = sdf.parse(dateInString);
				dateInString = "31-12-" + year;
				endDate = sdf.parse(dateInString);
			} else {
				throw new Exception("Quarter out of bounds");
			}
			
			return !(beDate.before(startDate) || bsDate.after(endDate));
			
		} catch(Exception e) {
			log.warn(e.getMessage());
			return false;
		}
	}
}
