package com.revaturelabs.caliber.batch.employee;

import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
  List<Employee> getAll() throws AccessDeniedException;

  Optional<Employee> getById(String id);

  Employee create(Employee employee);

  Employee update(Employee employee);

  Employee createOrUpdate(Employee employee);

  void delete(String id);
}
