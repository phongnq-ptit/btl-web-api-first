package com.example.phongpt176.models.enums;

public enum BillStatus {
  PENDING("PENDING"),
  COMPLETED("COMPLETED"),
  CANCEL("CANCEL"),
  NULL("null");

  private String label;

  BillStatus(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
