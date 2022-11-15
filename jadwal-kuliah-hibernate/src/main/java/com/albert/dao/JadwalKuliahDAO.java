package com.albert.dao;

import javax.enterprise.context.ApplicationScoped;
import com.albert.model.JadwalKuliah;

@ApplicationScoped
public class JadwalKuliahDAO extends BaseDAO<JadwalKuliah> {

  public JadwalKuliahDAO() {
    super();
    this.setEntityClass(JadwalKuliah.class);
  }

  @Override
  public void updateEntity(JadwalKuliah t, JadwalKuliah entity) {
    t.setHari(entity.getHari());
    t.setRuangan(entity.getRuangan());
    t.setWaktuMulai(entity.getWaktuMulai());
    t.setWaktuSelesai(entity.getWaktuSelesai());
  }
}
