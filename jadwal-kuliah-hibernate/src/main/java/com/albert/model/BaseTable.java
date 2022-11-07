package com.albert.model;

public abstract class BaseTable {

  private String id;

  public BaseTable(String id) {
    super();
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("BaseTable [id=").append(id).append("]");
    return builder.toString();
  }
}
