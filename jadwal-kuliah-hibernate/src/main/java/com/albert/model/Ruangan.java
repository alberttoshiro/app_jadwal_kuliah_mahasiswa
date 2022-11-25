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
@Table(name = "ruangan")
public class Ruangan extends BaseEntity {

  @Column(name = "nomor_ruangan")
  private String nomorRuangan;

  public Ruangan() {

  }

  public Ruangan(UUID id, String nomorRuangan) {
    super(id);
    this.nomorRuangan = nomorRuangan;
  }

}
