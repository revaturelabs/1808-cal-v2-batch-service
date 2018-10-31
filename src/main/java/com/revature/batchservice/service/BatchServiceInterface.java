package com.revature.batchservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.batchservice.entity.BatchEntity;

@Service
public interface BatchServiceInterface {
	
	public List<BatchEntity> findAllBatches();
	public BatchEntity findBatchById(Integer id);
	public List<BatchEntity> findCurrentBatches();
	public void createBatch(BatchEntity be) throws IllegalArgumentException;
	public void updateBatch(BatchEntity be);
	public void deleteBatch(BatchEntity be);
	public List<Integer> findBatchYears();
	
}
