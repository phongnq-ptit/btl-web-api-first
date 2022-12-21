package com.example.phongpt176.models.dto;

import com.example.phongpt176.models.enums.BillStatus;
import java.util.Map;

public class BillDto {
  private Long id;
  private String date;
  private BillStatus status;
  private Long userId;

  private Map<String, Object> listBooks;

  private Map<String, Object> userInfo;

  public BillDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public BillStatus getStatus() {
    return status;
  }

  public void setStatus(BillStatus status) {
    this.status = status;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Map<String, Object> getListBooks() {
    return listBooks;
  }

  public void setListBooks(Map<String, Object> listBooks) {
    this.listBooks = listBooks;
  }

  public Map<String, Object> getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(Map<String, Object> userInfo) {
    this.userInfo = userInfo;
  }
}
