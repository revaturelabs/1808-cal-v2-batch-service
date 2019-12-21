package com.revaturelabs.caliber.batch;

import org.springframework.security.access.AccessDeniedException;

import java.util.List;

public interface BatchService {
  List<Batch> getAll() throws AccessDeniedException;

  Batch getById(String id);

  Batch create(Batch batch);

  Batch update(Batch batch);

  Batch createOrUpdate(Batch batch);

  void delete(String id);
}
