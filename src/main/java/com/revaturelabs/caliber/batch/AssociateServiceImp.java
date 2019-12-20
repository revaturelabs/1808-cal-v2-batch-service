package com.revaturelabs.caliber.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociateServiceImp implements AssociateService {
  @Autowired
  AssociateRepository associateRepository;

  @Override
  public List<Associate> getAll() throws AccessDeniedException {
    return associateRepository.findAll();
  }

  @Override
  public Associate getById(String id) {
    return null;
  }

  @Override
  public Associate create(Associate batch) {
    return null;
  }

  @Override
  public Associate update(Associate category) {
    return null;
  }

  @Override
  public Associate createOrUpdate(Associate category) {
    return null;
  }

  @Override
  public void delete(String id) {

  }
}
