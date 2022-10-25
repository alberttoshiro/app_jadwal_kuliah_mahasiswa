package com.albert.model;

import java.time.LocalTime;

public class JadwalKuliah extends BaseScheduleTable {

  private static final long serialVersionUID = 7167094102699512026L;

  private String ruangan;
  private String hari;

  public JadwalKuliah() {
    super();
  }

  public JadwalKuliah(String id, LocalTime startTime, LocalTime endTime, String ruangan,
      String hari) {
    super(id, startTime, endTime);
    this.ruangan = ruangan;
    this.hari = hari;
  }

  public String getHari() {
    return hari;
  }

  public String getRuangan() {
    return ruangan;
  }

  public void setHari(String hari) {
    this.hari = hari;
  }

  public void setRuangan(String ruangan) {
    this.ruangan = ruangan;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("JadwalKuliah [ruangan=").append(ruangan).append(", hari=").append(hari)
        .append(", toString()=").append(super.toString()).append("]");
    return builder.toString();
  }

}
