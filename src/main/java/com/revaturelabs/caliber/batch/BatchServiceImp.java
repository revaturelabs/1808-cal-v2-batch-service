package com.revaturelabs.caliber.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.*;


import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
public class BatchServiceImp implements BatchService {
  private static final Logger logger = LoggerFactory.getLogger(BatchServiceImp.class);

  @Autowired
  private BatchRepository batchRepository;

  private List<String> getGroupsFromSecurityContext() throws AccessDeniedException {
    Object groups = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClaims().get("groups");

    if (groups instanceof String) {
      return Collections.singletonList((String) groups);
    }

    if (groups instanceof List) {
      @SuppressWarnings("unchecked")
        List<String> list = (List<String>) groups;
      return list;
    }

    throw new AccessDeniedException("No group claim");
  }

  private String getCurrentUserEmail() {
    return (String) ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClaims().get("email");
  }

  private List<String> globalAllowed = Arrays.asList("Application/ROLE_VP", "Application/ROLE_QC");

  private boolean allowedGlobalRead() {
    return !Collections.disjoint(getGroupsFromSecurityContext(), globalAllowed);
  }

  @Override
  public List<Batch> getAll() throws AccessDeniedException {
    if (allowedGlobalRead()) {
      return batchRepository.findAll();
    }

    String email = getCurrentUserEmail();
    return batchRepository.findByTrainerEmail(email);
  }

  @Override
  public Batch getById(String id) {
    return null;
  }

  @Override
  public Batch create(Batch batch) {
    return null;
  }

  @Override
  public Batch update(Batch category) {
    return null;
  }

  @Override
  public Batch createOrUpdate(Batch category) {
    return null;
  }

  @Override
  public void delete(String id) {

  }
}
