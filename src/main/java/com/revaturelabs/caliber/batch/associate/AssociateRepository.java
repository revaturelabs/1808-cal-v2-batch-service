package com.revaturelabs.caliber.batch.associate;

import com.revaturelabs.caliber.batch.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociateRepository extends JpaRepository<Associate,String> {
  @Query("FROM Batch AS batch INNER JOIN batch.traineeAssignments AS trainingAssignments WHERE trainingAssignments.associate.email = :email")
  public List<Batch> findByAssociateEmail(@Param("email") String email);
}
