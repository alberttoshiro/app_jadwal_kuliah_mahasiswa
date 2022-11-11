package com.albert.model;

import java.time.LocalTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.albert.util.HariUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "jadwal_kuliah")
public class JadwalKuliah extends BaseEntity implements Comparable<JadwalKuliah> {

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

  @Override
  public int compareTo(JadwalKuliah o) {
    int hari = HariUtil.getOrder(this.hari).compareTo(HariUtil.getOrder(o.hari));
    if (hari != 0) {
      return hari;
    }
    return this.waktuMulai.compareTo(o.waktuMulai);
  }
}
