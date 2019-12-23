package com.revature.batchservice.service;

import java.util.List;

import com.revature.batchservice.dto.Benchmark;
import org.springframework.stereotype.Service;

import com.revature.batchservice.entity.BatchEntity;

@Service
public interface BatchServiceInterface {
	
	public List<BatchEntity> findAllBatches();
	public BatchEntity findBatchById(int id);
	public List<BatchEntity> findCurrentBatches();
	public BatchEntity createBatch(BatchEntity be) throws IllegalArgumentException;
	public void updateBatch(BatchEntity be);
	public void deleteBatch(int batchId);
	public List<Integer> findBatchYears();
	public List<BatchEntity> findBatchesByYearAndQuarter(int year, int quarter);
	BatchEntity upsertBatch(BatchEntity batchEntity);
	Benchmark getBatchBenchmark(int batchId);
}
