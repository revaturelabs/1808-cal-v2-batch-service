package com.revaturelabs.caliber.batch.revpro.dto;

import java.util.Objects;

public class RevProResponse<T> {
  private Integer statusCode;
  private String description;
  private T data;

  public RevProResponse() {
  }

  public RevProResponse(Integer statusCode, String description, T data) {
    this.statusCode = statusCode;
    this.description = description;
    this.data = data;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RevProResponse<?> that = (RevProResponse<?>) o;
    return Objects.equals(getStatusCode(), that.getStatusCode()) &&
      Objects.equals(getDescription(), that.getDescription()) &&
      Objects.equals(getData(), that.getData());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getStatusCode(), getDescription(), getData());
  }

  @Override
  public String toString() {
    return "RevProResponse{" +
      "statusCode=" + statusCode +
      ", description='" + description + '\'' +
      ", data=" + data +
      '}';
  }
}
