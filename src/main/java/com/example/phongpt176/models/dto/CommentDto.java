package com.example.phongpt176.models.dto;

import com.example.phongpt176.models.Books;
import com.example.phongpt176.models.Users;

public class CommentDto {
  private Long id;
  private Users user;
  private Books book;
  private String comment;
  private int rate;

  public CommentDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Users getUser() {
    return user;
  }

  public void setUser(Users user) {
    this.user = user;
  }

  public Books getBook() {
    return book;
  }

  public void setBook(Books book) {
    this.book = book;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public int getRate() {
    return rate;
  }

  public void setRate(int rate) {
    this.rate = rate;
  }
}
