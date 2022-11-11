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
@Table(name = "jadwal_kuliah_mahasiswa", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"mahasiswa_id", "matakuliah_id", "jadwal_kuliah_id"})})
public class JadwalKuliahMahasiswa extends BaseEntity implements Comparable<JadwalKuliahMahasiswa> {

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

  @Override
  public int compareTo(JadwalKuliahMahasiswa o) {
    return this.jadwalKuliah.compareTo(o.jadwalKuliah);
  }
}
