package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entity.BatchEntity;

@Repository
public interface BatchRepository extends JpaRepository<BatchEntity, Integer> {
	
	public List<BatchEntity> findAllBatchesByYear (int year);
}
