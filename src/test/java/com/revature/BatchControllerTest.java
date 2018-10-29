package com.revature;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.batchservice.controller.BatchController;
import com.revature.batchservice.repository.BatchRepository;
import com.revature.batchservice.service.BatchService;

@RunWith(MockitoJUnitRunner.class)
public class BatchControllerTest {
	
	@Mock
    private BatchRepository br;
    
    @Mock
    private BatchService bsi;
    
    @InjectMocks
    @Autowired
    private BatchController bc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testControllerCreateMethod() {
		
	}

}
