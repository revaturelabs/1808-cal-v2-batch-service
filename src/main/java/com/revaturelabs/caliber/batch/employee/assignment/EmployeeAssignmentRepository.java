package com.revaturelabs.caliber.batch.employee.assignment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAssignmentRepository extends JpaRepository<EmployeeAssignment, EmployeeBatchId> {
}
