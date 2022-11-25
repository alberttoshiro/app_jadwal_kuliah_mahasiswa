package com.albert.model;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

  @ManyToMany
  @JoinTable(name = "mahasiswa_matakuliah", joinColumns = @JoinColumn(name = "mahasiswa_id"),
      inverseJoinColumns = @JoinColumn(name = "matakuliah_id"))
  private Set<Matakuliah> listMatakuliah;

  public Mahasiswa() {

  }

  public Mahasiswa(UUID id, String nim, String nama) {
    super(id);
    this.nim = nim;
    this.nama = nama;
  }
}
