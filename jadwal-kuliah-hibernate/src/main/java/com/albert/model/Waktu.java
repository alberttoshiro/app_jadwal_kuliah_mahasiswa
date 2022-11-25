package com.albert.model;

import java.time.LocalTime;
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
@Table(name = "waktu")
public class Waktu extends BaseEntity {

  @Column(name = "kode_waktu", unique = true)
  private Integer kodeWaktu;

  @Column(name = "waktu_mulai", unique = true)
  private LocalTime waktuMulai;

  @Column(name = "waktu_selesai", unique = true)
  private LocalTime waktuSelesai;

  public Waktu() {

  }

  public Waktu(UUID id, Integer kodeWaktu, LocalTime waktuMulai, LocalTime waktuSelesai) {
    super(id);
    this.kodeWaktu = kodeWaktu;
    this.waktuMulai = waktuMulai;
    this.waktuSelesai = waktuSelesai;
  }

}
