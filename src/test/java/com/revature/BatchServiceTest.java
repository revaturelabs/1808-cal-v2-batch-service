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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.revature.entity.BatchEntity;
import com.revature.repository.BatchRepository;
import com.revature.service.BatchService;
import com.revature.service.BatchServiceInterface;


public class BatchServiceTest {
    @Mock
    private BatchRepository br;
    
    @InjectMocks
    private  BatchServiceInterface bsi;
    
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
		be.setTrainer("Bill");
		be2.setTrainer("Tom");
		be3.setTrainer("Jahn");
		lbr.add(be);
		lbr.add(be2);
		lbr.add(be3);
		bsi = new BatchService();
		Mockito.when(br.save(be)).thenReturn(be);
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
