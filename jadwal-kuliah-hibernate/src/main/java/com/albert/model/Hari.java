package com.albert.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "hari")
public class Hari extends BaseEntity {

  @Column(name = "kode_hari")
  private Integer kodeHari;

  @Column(name = "nama_hari", unique = true)
  private String namaHari;

  public Hari() {

  }

  public Hari(UUID id, Integer kodeHari, String namaHari) {
    super(id);
    this.kodeHari = kodeHari;
    this.namaHari = namaHari;
  }

}
