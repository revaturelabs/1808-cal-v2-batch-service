package com.revature.batchservice.tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.batchservice.BatchServiceApplication;
import com.revature.batchservice.entity.BatchEntity;
import com.revature.batchservice.repository.BatchRepository;
import com.revature.batchservice.service.BatchService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BatchServiceApplication.class)
public class BatchRepositoryTest {
	
	@Autowired
	private BatchService bsi;

	BatchEntity be, be2, be3;
	List<BatchEntity> lbr;

	@Before
	public void setUp() throws Exception {
		
		be = new BatchEntity();
		lbr = new ArrayList<BatchEntity>();
		be2 = new BatchEntity();
		be3 = new BatchEntity();
		be.setBatchId(1);
		be2.setBatchId(2);
		be3.setBatchId(3);
		be.setTrainer("Bill");
		be2.setTrainer("Tom");
		be3.setTrainer("Jahn");
		be.setCoTrainer("beTrainer");
		be2.setCoTrainer("beTrainer2");
		be3.setCoTrainer("beTrainer3");
		be.setTrainingName("beTrainingName");
		be2.setTrainingName("beTrainingName2");
		be3.setTrainingName("beTrainingName3");
		be.setTrainingType("beTrainingType");
		be2.setTrainingType("beTrainingType2");
		be3.setTrainingType("beTrainingType3");
		be.setLocation("beLocation");
		be2.setLocation("beLocation2");
		be3.setLocation("beLocation3");
		be.setSkillType("skillType");
		be2.setSkillType("skillType2");
		be3.setSkillType("skillType3");
		Calendar startDate = Calendar.getInstance();
		startDate.set(2018, 10, 22);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2018, 10, 23);
		be.setStartDate(startDate.getTime());
		be.setEndDate(endDate.getTime());
		
		startDate.set(2018, 11, 22);
		endDate.set(2018, 11, 23);
		be2.setStartDate(startDate.getTime());
		be2.setEndDate(endDate.getTime());
		
		startDate.set(2019, 1, 22);
		endDate.set(2019, 1, 23);
		be3.setStartDate(startDate.getTime());
		be3.setEndDate(endDate.getTime());
		/*
		be.setStartDate(LocalDate.now());
		be.setEndDate(LocalDate.now().plusMonths(1));
		be2.setStartDate(LocalDate.now());
		be2.setEndDate(LocalDate.now().plusMonths(2));
		be3.setStartDate(LocalDate.now());
		be3.setEndDate(LocalDate.now().plusMonths(3));
		*/
		be.setGoodGrade(75);
		be2.setGoodGrade(85);
		be3.setGoodGrade(95);
		be.setPassingGrade(60);
		be2.setPassingGrade(80);
		be3.setPassingGrade(80);
				
		
		
		lbr.add(be);
		lbr.add(be2);
		lbr.add(be3);
		
	}

	 
	 
	@Test
	public void testFindAllBatchByYear() {
		Calendar startDate = Calendar.getInstance();
		startDate.set(2018, 10, 22);
		
		be.setStartDate(startDate.getTime());
		startDate.set(Calendar.YEAR, 2017);
		be2.setStartDate(startDate.getTime());
		startDate.set(Calendar.YEAR, 2016);
		be3.setStartDate(startDate.getTime());
		
		bsi.createBatch(be);
		bsi.createBatch(be2);
		bsi.createBatch(be3);
		
		List<Integer> expectedYears = new ArrayList<Integer>();
		
		expectedYears.add(2016);
		expectedYears.add(2017);
		expectedYears.add(2018);
		
		List<Integer> listYears = bsi.findBatchYears();
		for(Integer i: listYears) {
			System.out.println(i);
		}
		assertEquals(expectedYears, listYears);
		
	}
	
	@Test
	public void testFindCurrentBatches() {
		Calendar tempDate = Calendar.getInstance();
		
		int year = tempDate.get(Calendar.YEAR);
		int month = tempDate.get(Calendar.MONTH);
		int day = tempDate.get(Calendar.DAY_OF_MONTH);
		tempDate.add(Calendar.DAY_OF_MONTH, -1);
		//Valid starting time. Batch still active
		be.setStartDate(tempDate.getTime());
		tempDate.add(Calendar.YEAR, 1);
		be.setEndDate(tempDate.getTime());
		
		//Batch has not started.
		tempDate.add(Calendar.DAY_OF_MONTH, 2);
		be2.setStartDate(tempDate.getTime());
		tempDate.add(Calendar.DAY_OF_MONTH, 10);
		be2.setEndDate(tempDate.getTime());
		
		//Batch has already ended. 
		tempDate.set(Calendar.YEAR , year - 1);
		be3.setStartDate(tempDate.getTime());
		tempDate.add(Calendar.DAY_OF_MONTH, 3);
		be3.setEndDate(tempDate.getTime());
		
		bsi.createBatch(be);
		bsi.createBatch(be2);
		bsi.createBatch(be3);
		
		List<BatchEntity> listExpected = new ArrayList<BatchEntity>();
		listExpected.add(be);
		
		List<BatchEntity> listRecieved = bsi.findCurrentBatches();
		assertEquals(listExpected, listRecieved);
	}
	

}
