package com.revature.batchservice.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.batchservice.entity.BatchEntity;
import com.revature.batchservice.service.BatchService;

@RestController()
@RequestMapping("/batch")
public class BatchController {

	private Logger log = Logger.getLogger("BatchController");
	
	@Autowired
	private BatchService bs;
	
	@GetMapping()
	public List<BatchEntity> getAllBatches() {
		log.debug("Inside getAllBatches");
		System.out.println("Inside getAllBatches");
		return bs.findAllBatches();
	}
	
	@GetMapping("/{id}")
	public BatchEntity getBatcheById(@PathVariable("id") Integer id) {
		log.debug("Inside findBatchById");
		System.out.println("Inside findBatchById");
		return bs.findBatchById(id);
	}
	
	@PostMapping()
	public void createBatch(@RequestBody BatchEntity be) {
		log.debug("Inside createBatch");
		System.out.println("Inside createBatch");
		try {
			bs.createBatch(be);
		} catch (IllegalArgumentException e) {
			log.warn(e.getMessage() + "Inside BatchController.createBatch(BatchEntity) ");
		}
	}
	
	@PutMapping()
	public void updateBatch(@RequestBody BatchEntity be) {
		log.debug("Inside updateBatch");
		System.out.println("Inside updateBatch");
		bs.updateBatch(be);
	}
	
	@DeleteMapping()
	public void deleteBatch(@RequestBody BatchEntity be) {
		log.debug("Inside deleteBatch");
		System.out.println("Inside deleteBatch");
		bs.deleteBatch(be);
	}
}
