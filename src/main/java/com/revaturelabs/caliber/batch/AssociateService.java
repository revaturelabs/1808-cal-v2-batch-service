package com.revaturelabs.caliber.batch;

import org.springframework.security.access.AccessDeniedException;

import java.util.List;

public interface AssociateService {
  List<Associate> getAll() throws AccessDeniedException;

  Associate getById(String id);

  Associate create(Associate batch);

  Associate update(Associate category);

  Associate createOrUpdate(Associate category);

  void delete(String id);
}
