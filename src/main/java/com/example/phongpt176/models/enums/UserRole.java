package com.example.phongpt176.models.enums;

public enum UserRole {
  CLIENT("CLIENT"),
  ADMIN("ADMIN"),
  NULL("null");

  private String label;

  UserRole(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
