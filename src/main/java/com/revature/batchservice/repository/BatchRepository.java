package com.revature.batchservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.batchservice.entity.BatchEntity;
/**
 * A JPA repository for batch entities.  It has the default methods of a JpaRepository.
 * @author Justin Tu, Bita Mahbod, Daniel Mitre
 *
 */
@Repository(value="batchRepository")
public interface BatchRepository extends JpaRepository<BatchEntity, Integer> {
	/*
	@Query("select b from BatchEntity b where to_char(b.START_DATE, 'YYYY') = :year")
	public List<BatchEntity> findAllBatchesByYear (@Param("year") String year);
	*/
}