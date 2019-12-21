package com.revaturelabs.caliber.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
public class BatchServiceImp implements BatchService {
  private static final Logger logger = LoggerFactory.getLogger(BatchServiceImp.class);

  @Autowired
  private BatchRepository batchRepository;

  /*
   Below is service specific needs to apply business rules
   */

  private String getCurrentUserEmail() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth instanceof AnonymousAuthenticationToken) {
      return "";
    }
    return (String) ((Jwt) auth.getPrincipal()).getClaims().get("email");
  }

  private static List<GrantedAuthority> globalAllowed = Arrays.asList(new SimpleGrantedAuthority("Application/ROLE_VP"), new SimpleGrantedAuthority("Application/ROLE_QC"));

  /*
   Below is the implementations of the Batch service
   */

  private boolean allowedGlobalRead() {
    return !Collections.disjoint(SecurityContextHolder.getContext().getAuthentication().getAuthorities(), globalAllowed);
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
    return batchRepository.save(batch);
  }

  @Override
  public Batch update(Batch batch) {
    return null;
  }

  @Override
  public Batch createOrUpdate(Batch batch) {
    return batchRepository.save(batch);
  }

  @Override
  public void delete(String id) {

  }
}
