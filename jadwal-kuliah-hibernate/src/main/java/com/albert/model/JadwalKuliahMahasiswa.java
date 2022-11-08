package com.albert.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "jadwal_kuliah_mahasiswa", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"mahasiswa_id", "matakuliah_id", "jadwal_kuliah_id"})})
public class JadwalKuliahMahasiswa extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "mahasiswa_id")
  private Mahasiswa mahasiswa;

  @ManyToOne
  @JoinColumn(name = "matakuliah_id")
  private Matakuliah matakuliah;

  @ManyToOne
  @JoinColumn(name = "jadwal_kuliah_id")
  private JadwalKuliah jadwalKuliah;

  public JadwalKuliahMahasiswa() {
    super();
  }

  public JadwalKuliahMahasiswa(UUID id, Mahasiswa mahasiswa, Matakuliah matakuliah,
      JadwalKuliah jadwalKuliah) {
    super(id);
    this.mahasiswa = mahasiswa;
    this.matakuliah = matakuliah;
    this.jadwalKuliah = jadwalKuliah;
  }

  public JadwalKuliah getJadwalKuliah() {
    return jadwalKuliah;
  }

  public Mahasiswa getMahasiswa() {
    return mahasiswa;
  }

  public Matakuliah getMatakuliah() {
    return matakuliah;
  }

  public void setJadwalKuliah(JadwalKuliah jadwalKuliah) {
    this.jadwalKuliah = jadwalKuliah;
  }

  public void setMahasiswa(Mahasiswa mahasiswa) {
    this.mahasiswa = mahasiswa;
  }

  public void setMatakuliah(Matakuliah matakuliah) {
    this.matakuliah = matakuliah;
  }
}
