package com.revature.batchservice.tests;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.batchservice.BatchServiceApplication;
import com.revature.batchservice.controller.BatchController;
import com.revature.batchservice.entity.BatchEntity;
import com.revature.batchservice.service.BatchService;

/**
 * Tests integration between the BatchController, BatchService, and BatchRepository.
 * 
 * @author Justin Tu, Daniel Mitre
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BatchServiceApplication.class)
public class BatchIntegrationTest {
	
	@Autowired
	private BatchController bc;
	
	@Autowired
	private BatchService bs;
	
	private BatchEntity be1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		be1 = new BatchEntity("Training", "Java", "Servlets", "Nick", "", "Tampa", 
				LocalDate.now(), LocalDate.now().plusDays(2), 80, 80);
		System.out.println(LocalDate.now().getYear());
	}
	
	@Test
	public void testGetBatchesByStartYear() {
		System.out.println("starting batches by Year");
		bs.createBatch(be1);
		List<BatchEntity> listY = bs.findBatchesByStartYear(2018);
		List<BatchEntity> listBe = bs.findAllBatches();
		for(BatchEntity x: listY) {
			System.out.println(x.toString());
		}
		
	}

	@Test
	public void testCreateBatch() {
		bc.createBatch(be1);
	}

}
