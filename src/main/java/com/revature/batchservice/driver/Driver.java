package com.revature.batchservice.driver;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.batchservice.controller.BatchController;
import com.revature.batchservice.entity.BatchEntity;

/**
 * Sandbox driver for miscellaneous testing. Not part of any functionality.
 * @author Justin Tu, Daniel Mitre
 *
 */
public class Driver {
	
	@Autowired
	private static BatchController bc;
	
	
	public static void main(String[] args) {
		BatchEntity be1 = new BatchEntity("Training2", "C", "Other", "Nick", "", "Tampa", 
				LocalDate.now(), LocalDate.now().plusDays(2), 80, 80);
		bc.createBatch(be1);
	}

}
