package com.revaturelabs.caliber.batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditProvider")
public class AuditConfig {
  @Bean
  public AuditorAware<String> auditProvider() {
    return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
  }
}
