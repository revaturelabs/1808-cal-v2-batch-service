package com.revature.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.entity.BatchEntity;

@Service
public interface BatchServiceInterface {
	
	public List<BatchEntity> findAllBatches();
	public List<BatchEntity> findBatchesByYear(int year);
	public void createBatch(BatchEntity be);
	public void updateBatch(BatchEntity be);
	public void deleteBatch(BatchEntity be);
}
