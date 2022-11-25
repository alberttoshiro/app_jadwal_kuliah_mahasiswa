package com.albert.model;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class Matakuliah extends BaseEntity {

  @Column(name = "nama_matakuliah")
  private String namaMatakuliah;

  @ManyToMany(mappedBy = "listMatakuliah")
  private Set<Mahasiswa> listMahasiswa;

  public Matakuliah() {

  }

  public Matakuliah(UUID id, String namaMatakuliah) {
    super(id);
    this.namaMatakuliah = namaMatakuliah;
  }
}
