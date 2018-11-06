package com.revature.batchservice.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.revature.batchservice.entity.BatchEntity;
import com.revature.batchservice.feign.LocationClient;
import com.revature.batchservice.repository.BatchRepository;
import com.revature.batchservice.service.BatchService;

@RunWith(MockitoJUnitRunner.class)
public class BatchServiceTest {
    @Mock
    private BatchRepository br;
    
    @Mock
    private LocationClient lc;
    
    @InjectMocks
    @Autowired
    private BatchService bsi;
    
    BatchEntity be, be2, be3;
    List<BatchEntity> lbr;
    
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

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
		be.setLocationId(1);
		be2.setLocationId(2);
		be3.setLocationId(3);
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
		
		Mockito.when(br.save(be)).thenReturn(be);
		Mockito.when(br.save(be2)).thenReturn(be2);
		Mockito.when(br.save(be3)).thenReturn(be3);
		Mockito.when(br.findAll()).thenReturn(lbr);
		
		
		Mockito.when(lc.getLocationById(1)).
		thenReturn(new ResponseEntity<String>("1, Revature, 123 Sesame Street Tampa FL 11111", HttpStatus.ACCEPTED));
		Mockito.when(lc.getLocationById(2)).
		thenReturn(new ResponseEntity<String>("2, Cognizant, 321 Elm Street Dallas TX 22222", HttpStatus.ACCEPTED));
		Mockito.when(lc.getLocationById(3)).
		thenReturn(new ResponseEntity<String>("3, Ford, 111 Ford Street Detroit MI 33333", HttpStatus.ACCEPTED));
				
	}
		
	@Test
	public void testCreateBatch() {
		bsi.createBatch(be);
		bsi.createBatch(be2);
		bsi.createBatch(be3);
		List<BatchEntity> result = bsi.findAllBatches();
		assertEquals(lbr, result);
		
	}

	@Test
	public void testUpdateBatch() {
		
		bsi.createBatch(be);
		BatchEntity updated = new BatchEntity();
		updated.setBatchId(be.getBatchId());
		updated.setTrainer("Larry");
	    bsi.updateBatch(updated);
	    
	    List<BatchEntity> updatedList = new ArrayList<BatchEntity>();
	    updatedList.add(updated);
	    Mockito.when(br.findAll()).thenReturn(updatedList);
	  
		assertEquals(updatedList, bsi.findAllBatches());
		
	}
	
	@Test
	public void testDeleteBatch() {
		
		BatchEntity saved = bsi.createBatch(be);
		
	    bsi.deleteBatch(saved.getBatchId());
	    Mockito.verify(br).delete(be.getBatchId());
	    List<BatchEntity> deletedList = new ArrayList<BatchEntity>();
	    
	    Mockito.when(br.findAll()).thenReturn(deletedList);
	  
		assertEquals(deletedList, bsi.findAllBatches());
		
	}
	
	@Test
	public void testCreateBatchValidation() {
		be.setGoodGrade(20);
		be.setPassingGrade(75);
		
		
		Calendar startDate = Calendar.getInstance();
		startDate.set(2018, 10, 22);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2018, 10, 20);
		
		be2.setStartDate(startDate.getTime());
		be2.setEndDate(endDate.getTime());
		
		BatchEntity[] beArray = new BatchEntity[10];
		beArray[0] = new BatchEntity(null, "a", "a", "a", "", 10, endDate.getTime(), startDate.getTime(), 1, 1);
		beArray[1] = new BatchEntity("a", null, "a", "a", "", 10, endDate.getTime(), startDate.getTime(), 1, 1);
		beArray[2] = new BatchEntity("a", "a", null, "a", "", 10, endDate.getTime(), startDate.getTime(), 1, 1);
		beArray[3] = new BatchEntity("a", "a", "a", null, "", 10, endDate.getTime(), startDate.getTime(), 1, 1);
		beArray[4] = new BatchEntity("a", "a", "a", "a", null, 10, endDate.getTime(), startDate.getTime(), 1, 1);
		beArray[5] = new BatchEntity("a", "a", "a", "a", "", null, endDate.getTime(), startDate.getTime(), 1, 1);
		beArray[6] = new BatchEntity("a", "a", "a", "a", "", 10, null, startDate.getTime(), 1, 1);
		beArray[7] = new BatchEntity("a", "a", "a", "a", "", 10, endDate.getTime(), null, 1, 1);
		beArray[8] = new BatchEntity("a", "a", "a", "a", "", 10, endDate.getTime(), startDate.getTime(), null, 1);
		beArray[9] = new BatchEntity("a", "a", "a", "a", "", 10, endDate.getTime(), startDate.getTime(), 1, null);
		

		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Passing Grade can not be greater than Good Grade.");
		bsi.createBatch(be);
		
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("End Date must be After Start date.");
		bsi.createBatch(be2);
		
		for (int i =0; i<beArray.length; i++) {
			exceptionRule.expect(NullPointerException.class);
			bsi.createBatch(beArray[i]);
		}
		
	}
	
	@Test
	public void testLocationClient() {
		List<BatchEntity> fabl = bsi.findAllBatches();
		//These values are based on hardcoded dummy values from the Location Microservice
		String expected1 = "Revature, 123 Sesame Street Tampa FL 11111";
		String expected2 = "Cognizant, 321 Elm Street Dallas TX 22222";
		String expected3 = "Ford, 111 Ford Street Detroit MI 33333";
		
		for(BatchEntity entity: fabl) {
			if(entity.getLocationId() == 1) {
				assertEquals(expected1, entity.getLocationName());
			} else if (entity.getLocationId() == 2) {
				assertEquals(expected2, entity.getLocationName());
			} else if (entity.getLocationId() == 3) {
				assertEquals(expected3, entity.getLocationName());
			}
		}
	}
}
