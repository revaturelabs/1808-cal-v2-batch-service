package com.revature.batchservice.tests;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.batchservice.BatchServiceApplication;
import com.revature.batchservice.entity.BatchEntity;
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
		be.setLocationId(10);
		be2.setLocationId(20);
		be3.setLocationId(30);
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
		bsi.createBatch(be);
		bsi.createBatch(be2);
		bsi.createBatch(be3);
		
		List<BatchEntity> list2018 = new ArrayList<BatchEntity>();
		list2018.add(be);
		list2018.add(be2);
		
		List<BatchEntity> list2019 = new ArrayList<BatchEntity>();
		list2019.add(be3);
		
		List<BatchEntity> received2018 = bsi.findBatchesByYear(2018);
		List<BatchEntity> received2019 = bsi.findBatchesByYear(2019);
		assertEquals(list2018,received2018);
		assertEquals(list2019,  received2019);
		
	}
	
	@Test
	public void testFindCurrentBatches() {
		Calendar tempDate = Calendar.getInstance();
		String format = "yyyy-MM-dd HH:mm:ss.S";
		SimpleDateFormat formatter = new SimpleDateFormat();
		formatter.applyPattern(format);
		tempDate.set(Calendar.HOUR_OF_DAY, 0);
		tempDate.set(Calendar.MINUTE, 0);
		tempDate.set(Calendar.SECOND, 0);
		tempDate.set(Calendar.MILLISECOND, 0);
		
		
		int year = tempDate.get(Calendar.YEAR);
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

	
	@Test
	public void testFindBatchYears() {
		bsi.createBatch(be);
		bsi.createBatch(be2);
		bsi.createBatch(be3);
		
		List<Integer> recievedYears = bsi.findBatchYears();
		
		int countOf2018 = 0;
		int countOf2019 = 0;
		
		//Check that one 2018 and one 2019 was retrieved.
		for(Integer i : recievedYears) {
			if(i.intValue() == 2018) {
				countOf2018++;
			}
			if(i.intValue() == 2019) {
				countOf2019++;
			}
		}
		int expectedCountOf2018 = 1;
		int expectedCountOf2019 = 1;		
		assertEquals(expectedCountOf2018, countOf2018);
		assertEquals(expectedCountOf2019, countOf2019);
	}	
}
