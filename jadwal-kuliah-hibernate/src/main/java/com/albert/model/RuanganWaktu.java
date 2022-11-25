package com.albert.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "ruangan_waktu",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"ruangan_id", "hari_id", "waktu_id"})})
public class RuanganWaktu extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "ruangan_id")
  private Ruangan ruangan;

  @ManyToOne
  @JoinColumn(name = "hari_id")
  private Hari hari;

  @ManyToOne
  @JoinColumn(name = "waktu_id")
  private Waktu waktu;

  public RuanganWaktu() {

  }

  public RuanganWaktu(UUID id, Ruangan ruangan, Hari hari, Waktu waktu) {
    super(id);
    this.ruangan = ruangan;
    this.hari = hari;
    this.waktu = waktu;
  }

}
