package com.example.phongpt176.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "carts")
public class Carts {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long userId;
  private Long bookId;
  private int quantity;
  private int status;
  @Transient
  @ManyToOne
  private Users user;
  @Transient
  @ManyToOne
  private Books books;

  public Carts() {
  }

  public Carts(Long id, int quantity, Users user, Books books) {
    this.id = id;
    this.quantity = quantity;
    this.user = user;
    this.books = books;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @JsonIgnore
  public Long getUserId() {
    return userId;
  }

  @JsonProperty
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @JsonIgnore
  public Long getBookId() {
    return bookId;
  }

  @JsonProperty
  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @JsonIgnore
  public int getStatus() {
    return status;
  }

  @JsonProperty
  public void setStatus(int status) {
    this.status = status;
  }

  public Users getUser() {
    return user;
  }

  public void setUser(Users user) {
    this.user = user;
  }

  public Books getBooks() {
    return books;
  }

  public void setBooks(Books books) {
    this.books = books;
  }
}
