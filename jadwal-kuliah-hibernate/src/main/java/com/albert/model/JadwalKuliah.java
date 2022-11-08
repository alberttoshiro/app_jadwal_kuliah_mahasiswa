package com.albert.model;

import java.time.LocalTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "jadwal_kuliah")
public class JadwalKuliah extends BaseEntity {

  @Column(name = "hari")
  private String hari;

  @Column(name = "ruangan")
  private String ruangan;

  @Column(name = "waktu_mulai")
  private LocalTime waktuMulai;

  @Column(name = "waktu_selesai")
  private LocalTime waktuSelesai;

  public JadwalKuliah() {
    super();
  }

  public JadwalKuliah(UUID id, String hari, String ruangan, LocalTime waktuMulai,
      LocalTime waktuSelesai) {
    super(id);
    this.hari = hari;
    this.ruangan = ruangan;
    this.waktuMulai = waktuMulai;
    this.waktuSelesai = waktuSelesai;
  }

  public String getHari() {
    return hari;
  }

  public String getRuangan() {
    return ruangan;
  }

  public LocalTime getWaktuMulai() {
    return waktuMulai;
  }

  public LocalTime getWaktuSelesai() {
    return waktuSelesai;
  }

  public void setHari(String hari) {
    this.hari = hari;
  }

  public void setRuangan(String ruangan) {
    this.ruangan = ruangan;
  }

  public void setWaktuMulai(LocalTime waktuMulai) {
    this.waktuMulai = waktuMulai;
  }

  public void setWaktuSelesai(LocalTime waktuSelesai) {
    this.waktuSelesai = waktuSelesai;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("JadwalKuliah [hari=").append(hari).append(", ruangan=").append(ruangan)
        .append("]");
    return builder.toString();
  }
}
