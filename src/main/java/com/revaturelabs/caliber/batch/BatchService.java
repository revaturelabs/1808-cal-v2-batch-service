package com.revaturelabs.caliber.batch;

import org.springframework.security.access.AccessDeniedException;

import java.util.List;

public interface BatchService {
  List<Batch> getAll() throws AccessDeniedException;

  Batch getById(String id);

  Batch create(Batch batch);

  Batch update(Batch category);

  Batch createOrUpdate(Batch category);

  void delete(String id);
}
