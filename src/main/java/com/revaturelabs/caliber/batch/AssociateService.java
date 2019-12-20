package com.revaturelabs.caliber.batch;

import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.util.Optional;

public interface AssociateService {
  List<Associate> getAll() throws AccessDeniedException;

  Optional<Associate> getById(String id);

  Associate create(Associate associate);

  Associate update(Associate associate);

  Associate createOrUpdate(Associate associate);

  void delete(String id);
}
