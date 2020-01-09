package com.revaturelabs.caliber.batch.revpro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Transient;
import java.util.Objects;

@Configuration
public class RevProConfig {
  @Value("${revature.revpro.api.username}")
  private String revProUsername;

  @Value("${revature.revpro.api.password}")
  @Transient
  private String getRevProPassword;

  public RevProConfig() {
  }

  public String getRevProUsername() {
    return revProUsername;
  }

  public void setRevProUsername(String revProUsername) {
    this.revProUsername = revProUsername;
  }

  public String getGetRevProPassword() {
    return getRevProPassword;
  }

  public void setGetRevProPassword(String getRevProPassword) {
    this.getRevProPassword = getRevProPassword;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RevProConfig that = (RevProConfig) o;
    return Objects.equals(getRevProUsername(), that.getRevProUsername()) &&
      Objects.equals(getGetRevProPassword(), that.getGetRevProPassword());
  }

  @Override
  public String toString() {
    return "RevProConfig{" +
      "revProUsername='" + revProUsername + '\'' +
      ", getRevProPassword='" + getRevProPassword + '\'' +
      '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(getRevProUsername(), getGetRevProPassword());
  }
}
