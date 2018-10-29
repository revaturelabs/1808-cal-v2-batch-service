package com.revature.batchservice.tests;

import java.time.LocalDate;
import java.util.Calendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.batchservice.controller.BatchController;
import com.revature.batchservice.entity.BatchEntity;
import com.revature.batchservice.service.BatchService;

@RunWith(MockitoJUnitRunner.class)
public class BatchControllerTest {
    
    @Mock
    private BatchService bsi;
    
    @InjectMocks
    @Autowired
    private BatchController bc;
    
    @Autowired
    private BatchController realBc;
    
    private BatchEntity be;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Calendar startDate = Calendar.getInstance();
		startDate.set(2018, 10, 22);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2018, 10, 23);
		be = new BatchEntity("Training", "Java", "Servlets", "Nick", "", "Tampa", 
				startDate.getTime(), endDate.getTime(), 80, 80);
	}

	@Test
	public void testControllerCreateMethod() {
		bc.createBatch(be);
		Mockito.verify(bsi).createBatch(be);
	}
	
	@Test
	public void testGetAllBatches() {
		bc.getAllBatches();
		Mockito.verify(bsi).findAllBatches();
	}
	
	@Test
	public void testFindBatchById() {
		bc.getBatchById(0);
		Mockito.verify(bsi).findBatchById(0);
	}
}
