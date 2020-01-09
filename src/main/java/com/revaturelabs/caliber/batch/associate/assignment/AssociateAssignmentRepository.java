package com.revaturelabs.caliber.batch.associate.assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateAssignmentRepository extends JpaRepository<AssociateAssignment, AssociateAssignmentId> {
}
