package com.revature.batchservice.controller;

import java.util.List;

import com.revature.batchservice.dto.Benchmark;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.revature.batchservice.entity.BatchEntity;
import com.revature.batchservice.service.BatchService;

/**
 * The BatchController is responsible for handling request about batches. It is mapped to CaliberProjectURL/batch.
 * The BatchController can return a list of batches, a batch by Id.  It can add to, update, and delete a batch from our database.
 * The BatchController expects request in JSON and always sends responses in JSON.
 * Unless specified otherwise, by the method.
 * 
 * @author Justin Tu, Bita Mahbod, Daniel Mitre
 *
 */
@RestController()
@CrossOrigin("*")
public class BatchController {

	private Logger log = Logger.getLogger("BatchController");
	
	@Autowired
	private BatchService bs;
	
	/**
	 * Accepts a HTTP GET request. 
	 * Returns a List of all BatchEntities on the connected database as a JSON object.
	 * @return a List<BatchEntity> that contains all BatchEntities in the database.
	 */
	@GetMapping({ "/qc/batch/all", "/vp/batch/all" })
	public List<BatchEntity> getAllBatches(@RequestParam(name="year", required=false) int year, @RequestParam(name="quarter", required=false) int quarter) {
		log.debug("Inside getAllBatches");
		List<BatchEntity> total = null;
		if(year != 0 && quarter != 0) {
			total = bs.findBatchesByYearAndQuarter(year, quarter);
		} else {
			if(year != 0) {
				total = bs.findBatchesByYear(year);
			} else {
				total = bs.findAllBatches();
			}
		}
		return total;
	}
	
	/**
	 * Accepts a HTTP GET request. Mapped to ProjectURL/id/{pathVariable}
	 * Returns a BatchEntity as a JSON entity based on the given id.
	 * @param id An Integer that contains the BatchEntity id. The id is passed as a path variable. 
	 * @return a BatchEntity which has the same id as the given id. Null if no matching 
	 * 			id was found. Value is returned as a JSON object.
	 */
	@GetMapping("all/batch/{id}")
	public BatchEntity getBatchById(@PathVariable("id") int id) {
		log.debug("Inside findBatchById");
		return bs.findBatchById(id);
	}
	
	/**
	 * Accepts a HTTP Get Request. Mapped to ProjectURL/year/{path variable}
	 * Returns a List<BatchEntity> which contains Batches in the given year.
	 * The List is returned as a JSON Object.
	 */
	@GetMapping({"/qc/batch/{startYear}",	"/vp/batch/{startYear}"})
	public List<BatchEntity> getBatchesByYear(@PathVariable("startYear") int startYear) {
		log.debug("Inside getBatchesByYear");
		return bs.findBatchesByYear(startYear);
	}
	
	/*
	 * This function gets batches by year and by quarter to save time when loading large
	 * amounts of batches.. this way we only have to load a quarter of the batches!
	 */
	@GetMapping({"/qc/batch/{startYear}/{quarter}",	"/vp/batch/{startYear}/{quarter}"})
	public List<BatchEntity> getBatchesByYearByQuarter(@PathVariable("startYear") int startYear, @PathVariable("quarter") int quarter) {
		log.debug("Inside getBatchesByYearByQuarter");
		return bs.findBatchesByYearAndQuarter(startYear, quarter);
	}
	/**
	 * Accepts a HTTP Get Request. Mapped to ProjectURL/vp/batch/all/current
	 * Returns a List<BatchEntity> which contains current batches; which means the current date is 
	 * between their start date and end date.
	 * The List is returned as a JSON object.
	 * @return A List<BatchEntity> of current batches.
	 */
	@GetMapping("/vp/batch/all/current")
	public List<BatchEntity> getAllCurrentBatches(){
		log.debug("Inside getAllCurrentBatches");
		return bs.findCurrentBatches();
	}
	
	
	/**
	 *  Accepts a HTTP POST request.
	 *  Attempts to add a BatchEntity to the database. 
	 *  @param be The BatchEntity to add to the database.
	 * 
	 */
	@PostMapping("/all/batch/create")
	public void createBatch(@RequestBody BatchEntity be) {
		log.debug("Inside createBatch");
		try {
			bs.createBatch(be);
		} catch (IllegalArgumentException e) {
			log.warn(e.getMessage() + "Inside BatchController.createBatch(BatchEntity) ");
		}
	}
	
	/**
	 * Accepts a HTTP PUT request.
	 * Takes in a BatchEntity and updates any matching BatchEntity in the database. If
	 * no BatchEntity on the database has a matching id, then the given BatchEntity is
	 * added to the database.
	 * 
	 * @param be The BatchEntity to update.
	 */
	@PutMapping("/all/batch/update")
	public ResponseEntity<BatchEntity> updateBatch(@RequestBody BatchEntity be) {
		try {
			BatchEntity batch = bs.upsertBatch(be);
			return ResponseEntity.ok(batch);
		} catch (Exception e) {
			log.warn("Failed to upsert batch {}", e);
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	/**
	 * Accepts a HTTP DELETE request.
	 * Takes in a BatchEntity and attempts to delete it from the database. 
	 * If the give BatchEntity does not exist in the database, the database will not be changed.
	 */
	@DeleteMapping("/all/batch/delete/{batchId}")
	public void deleteBatch(@PathVariable int batchId) {
		log.debug("Inside deleteBatch");
		bs.deleteBatch(batchId);
	}
	
	/**
	 * Accepts a HTTP GET Request 
	 * This method finds all batch start years and returns a List<Integer>.
	 * List is in ascending order and holds only distinct years.
	 * @return A List<Integer> filled with batch start years 
	 */
	@Transactional(readOnly = true)
	@GetMapping("/all/batch/valid_years")
	public List<Integer> batchYears(){
		return bs.findBatchYears();
	}

	@GetMapping("/{batchId}/benchmark")
	public ResponseEntity<Benchmark> getBenchmarkForBatch(@PathVariable("batchId") int batchId) {
		Benchmark benchmark = this.bs.getBatchBenchmark(batchId);
		if (benchmark != null) {
			return ResponseEntity.ok(benchmark);
		}
		return ResponseEntity.noContent().build();
	}
	
}
