package com.revaturelabs.caliber.batch.associate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociateServiceImp implements AssociateService {
  @Autowired
  AssociateRepository associateRepository;

  @Override
  public List<Associate> getAll() throws AccessDeniedException {
    return associateRepository.findAll();
  }

  @Override
  public Optional<Associate> getById(String id) {
    return associateRepository.findById(id);
  }

  @Override
  public Associate create(Associate associate) {
    return associateRepository.save(associate);
  }

  @Override
  public Associate update(Associate associate) {
    return null;
  }

  @Override
  public Associate createOrUpdate(Associate associate) {
    return associateRepository.save(associate);
  }

  @Override
  public void delete(String id) {

  }
}
