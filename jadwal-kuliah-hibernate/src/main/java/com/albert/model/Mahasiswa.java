package com.albert.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Mahasiswa extends BaseEntity {

  @Column(name = "nim", unique = true)
  private String nim;

  @Column(name = "nama")
  private String nama;

  public Mahasiswa() {
    super();
  }

  public Mahasiswa(UUID id, String nim, String nama) {
    super(id);
    this.nim = nim;
    this.nama = nama;
  }

  public String getNama() {
    return nama;
  }

  public String getNim() {
    return nim;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public void setNim(String nim) {
    this.nim = nim;
  }
}
