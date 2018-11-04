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
	}

}
