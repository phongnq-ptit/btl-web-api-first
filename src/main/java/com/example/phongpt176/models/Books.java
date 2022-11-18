package com.example.phongpt176.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "books")
public class Books {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String author;
  private String date;
  private int page;
  private String description;
  private Long categoryId;
  @ManyToOne
  @Transient
  private Categories category;
  @OneToOne
  @Transient
  private Images image;

  public Books() {
  }

  public Books(Long id, String title, String author, String date, int page, String description,
      Categories category, Images image) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.date = date;
    this.page = page;
    this.description = description;
    this.category = category;
    this.image = image;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Categories getCategory() {
    return category;
  }

  public void setCategory(Categories category) {
    this.category = category;
  }

  public Images getImage() {
    return image;
  }

  public void setImage(Images image) {
    this.image = image;
  }

  @JsonIgnore
  public Long getCategoryId() {
    return categoryId;
  }

  @JsonProperty
  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }
}
