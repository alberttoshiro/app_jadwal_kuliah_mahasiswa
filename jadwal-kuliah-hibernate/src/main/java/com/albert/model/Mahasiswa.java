package com.albert.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
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
}