package com.revature.batchservice.tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.batchservice.entity.BatchEntity;
import com.revature.batchservice.repository.BatchRepository;
import com.revature.batchservice.service.BatchService;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes= {AppConfig.class})
@DataJpaTest
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
		
	}

	 
	 
	@Test
	public void testFindAllBatchByYear() {
		be.setStartDate(LocalDate.of(2018,10,10));
		be2.setStartDate(LocalDate.of(2019,10,10));
		be3.setStartDate(LocalDate.of(2019,10,10));
		be.setEndDate(LocalDate.of(2018,10,11));
		be2.setEndDate(LocalDate.of(2019,10,11));
		be3.setEndDate(LocalDate.of(2019,10,11));
		
		bsi.createBatch(be);
		bsi.createBatch(be2);
		bsi.createBatch(be3);
	}

}
