package com.revaturelabs.caliber.batch.category;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@FeignClient(name = "category", url = "http://localhost:10000/")
public interface CategoryClient {
  @GetMapping(value = "/category", consumes = "application/json")
  List<Category> getCategories();

  public class Category {
    private int id;

    private String name;

    private Date createdOn;

    private String createdBy;


    private Date updatedOn;

    private String updatedBy;

    public Category() {
    }

    public Category(String name) {
      this.name = name;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Date getCreatedOn() {
      return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
      this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
      return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
      this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
      return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
      this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
      return "Category{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", createdOn=" + createdOn +
        ", createdBy='" + createdBy + '\'' +
        ", updatedOn=" + updatedOn +
        ", updatedBy='" + updatedBy + '\'' +
        '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Category category = (Category) o;
      return getId() == category.getId() &&
        Objects.equals(getName(), category.getName()) &&
        Objects.equals(getCreatedOn(), category.getCreatedOn()) &&
        Objects.equals(getCreatedBy(), category.getCreatedBy()) &&
        Objects.equals(getUpdatedOn(), category.getUpdatedOn()) &&
        Objects.equals(getUpdatedBy(), category.getUpdatedBy());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getId(), getName(), getCreatedOn(), getCreatedBy(), getUpdatedOn(), getUpdatedBy());
    }

    public String getCreatedBy() {
      return createdBy;
    }

    public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
    }
  }
}
