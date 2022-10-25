package com.albert.model;

import java.time.LocalTime;

public class JadwalKuliahMahasiswa extends JadwalKuliah {

  private static final long serialVersionUID = -6358044350661276227L;

  private String idMahasiswa;
  private String nim;
  private String idMatakuliah;
  private String namaMatakuliah;

  public JadwalKuliahMahasiswa() {
    super();
  }

  public JadwalKuliahMahasiswa(String id, LocalTime startTime, LocalTime endTime, String ruangan,
      String hari, String idMahasiswa, String nim, String idMatakuliah, String namaMatakuliah) {
    super(id, startTime, endTime, ruangan, hari);
    this.idMahasiswa = idMahasiswa;
    this.nim = nim;
    this.idMatakuliah = idMatakuliah;
    this.namaMatakuliah = namaMatakuliah;
  }

  public String getIdMahasiswa() {
    return idMahasiswa;
  }

  public String getIdMatakuliah() {
    return idMatakuliah;
  }

  public String getNamaMatakuliah() {
    return namaMatakuliah;
  }

  public String getNim() {
    return nim;
  }

  public void setIdMahasiswa(String idMahasiswa) {
    this.idMahasiswa = idMahasiswa;
  }

  public void setIdMatakuliah(String idMatakuliah) {
    this.idMatakuliah = idMatakuliah;
  }

  public void setNamaMatakuliah(String namaMatakuliah) {
    this.namaMatakuliah = namaMatakuliah;
  }

  public void setNim(String nim) {
    this.nim = nim;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("JadwalKuliahMahasiswa [idMahasiswa=").append(idMahasiswa).append(", nim=")
        .append(nim).append(", idMatakuliah=").append(idMatakuliah).append(", namaMatakuliah=")
        .append(namaMatakuliah).append(", toString()=").append(super.toString()).append("]");
    return builder.toString();
  }

}
