package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entity.BatchEntity;

@Repository
public interface BatchRepository extends JpaRepository<BatchEntity, Integer> {

}
