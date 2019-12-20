package com.revaturelabs.caliber.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
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
