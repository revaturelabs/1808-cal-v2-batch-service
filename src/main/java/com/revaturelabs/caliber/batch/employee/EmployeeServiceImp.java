package com.revaturelabs.caliber.batch.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public List<Employee> getAll() throws AccessDeniedException {
    return null;
  }

  @Override
  public Optional<Employee> getById(String id) {
    return employeeRepository.findById(id);
  }

  @Override
  public Employee create(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public Employee update(Employee employee) {
    return null;
  }

  @Override
  public Employee createOrUpdate(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public void delete(String id) {

  }
}
