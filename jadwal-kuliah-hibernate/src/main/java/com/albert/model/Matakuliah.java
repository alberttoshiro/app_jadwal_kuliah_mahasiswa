package com.albert.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Matakuliah extends BaseEntity {

  @Column(name = "nama_matakuliah")
  private String namaMatakuliah;

  public Matakuliah() {
    super();
  }

  public Matakuliah(UUID id, String namaMatakuliah) {
    super(id);
    this.namaMatakuliah = namaMatakuliah;
  }

  public String getNamaMatakuliah() {
    return namaMatakuliah;
  }

  public void setNamaMatakuliah(String namaMatakuliah) {
    this.namaMatakuliah = namaMatakuliah;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Matakuliah [namaMatakuliah=").append(namaMatakuliah).append("]");
    return builder.toString();
  }
}
