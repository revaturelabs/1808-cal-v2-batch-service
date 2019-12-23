package com.revaturelabs.caliber.batch;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.util.Optional;

public interface BatchService {
  List<Batch> getAll() throws AccessDeniedException;

  Optional<Batch> getById(String id);

  Batch create(Batch batch);

  Batch update(Batch batch);

  Batch createOrUpdate(Batch batch);

  public List<Batch> createOrUpdateAll(List<Batch> batches);

  void delete(String id);
}
