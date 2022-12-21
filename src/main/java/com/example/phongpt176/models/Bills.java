package com.example.phongpt176.models;

import com.example.phongpt176.models.enums.BillStatus;
import java.util.ArrayList;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bills")
public class Bills {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String date;
  private BillStatus status;
  private Long userId;

  @Column(name = "list_books",columnDefinition = "json")
  private String listBooks;

  @Column(name = "user_info",columnDefinition = "json")
  private String userInfo;

  @Transient
  @OneToMany
  private ArrayList<Carts> listProducts;

  @Transient
  private Map<String, Object> info;

  public Bills() {
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

  public String getListBooks() {
    return listBooks;
  }

  public void setListBooks(String listBooks) {
    this.listBooks = listBooks;
  }

  public String getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(String userInfo) {
    this.userInfo = userInfo;
  }

  public ArrayList<Carts> getListProducts() {
    return listProducts;
  }

  public void setListProducts(ArrayList<Carts> listProducts) {
    this.listProducts = listProducts;
  }

  public Map<String, Object> getInfo() {
    return info;
  }

  public void setInfo(Map<String, Object> info) {
    this.info = info;
  }
}
