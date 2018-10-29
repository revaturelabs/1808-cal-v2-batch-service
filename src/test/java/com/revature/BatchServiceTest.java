package com.revature;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
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

import com.revature.batchservice.entity.BatchEntity;
import com.revature.batchservice.repository.BatchRepository;
import com.revature.batchservice.service.BatchService;

@RunWith(MockitoJUnitRunner.class)
public class BatchServiceTest {
    @Mock
    private BatchRepository br;
    
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
		be.setLocation("beLocation");
		be2.setLocation("beLocation2");
		be3.setLocation("beLocation3");
		be.setSkillType("skillType");
		be2.setSkillType("skillType2");
		be3.setSkillType("skillType3");
		be.setStartDate(LocalDate.now());
		be.setEndDate(LocalDate.now().plusMonths(1));
		be2.setStartDate(LocalDate.now());
		be2.setEndDate(LocalDate.now().plusMonths(2));
		be3.setStartDate(LocalDate.now());
		be3.setEndDate(LocalDate.now().plusMonths(3));
		be.setGoodGrade(75);
		be2.setGoodGrade(85);
		be3.setGoodGrade(95);
		be.setPassingGrade(60);
		be2.setPassingGrade(80);
		be3.setPassingGrade(80);
				
		
		
		lbr.add(be);
		lbr.add(be2);
		lbr.add(be3);
		//bsi = new BatchService();
		Mockito.when(br.save(be)).thenReturn(be);
		Mockito.when(br.save(be2)).thenReturn(be2);
		Mockito.when(br.save(be3)).thenReturn(be3);
		Mockito.when(br.findAll()).thenReturn(lbr);
		
		
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
		
		bsi.createBatch(be);
		
	    bsi.deleteBatch(be);
	    Mockito.verify(br).delete(be);
	    List<BatchEntity> deletedList = new ArrayList<BatchEntity>();
	    
	    Mockito.when(br.findAll()).thenReturn(deletedList);
	  
		assertEquals(deletedList, bsi.findAllBatches());
		
	}
	
	@Test
	public void testCreateBatchValidation() {
		be.setGoodGrade(20);
		be.setPassingGrade(75);
		be.setStartDate(LocalDate.now().plusDays(2));
		be.setEndDate(LocalDate.now());
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Passing Grade can not be greater than Good Grade.");
		
		bsi.createBatch(be);
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("End Date must be After Start date.");
		
		bsi.createBatch(be2);
		
	}
}
