package com.revaturelabs.caliber.batch;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, String> {
  @Query("FROM Batch AS batch INNER JOIN batch.employeeAssignments AS trainingAssignments WHERE trainingAssignments.employee.email = :email")
  public List<Batch> findByTrainerEmail(@Param("email") String email);
}
