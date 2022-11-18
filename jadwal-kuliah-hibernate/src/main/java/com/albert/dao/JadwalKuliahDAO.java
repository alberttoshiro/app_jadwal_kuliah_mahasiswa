package com.albert.dao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.albert.model.JadwalKuliah;

@ApplicationScoped
public class JadwalKuliahDAO extends BaseDAO<JadwalKuliah> {

  @Inject
  EntityManager entityManager;

  @PostConstruct
  public void init() {
    setEntityClass(JadwalKuliah.class);
    setEntityManager(entityManager);
  }

  @Override
  public void updateEntity(JadwalKuliah t, JadwalKuliah entity) {
    t.setHari(entity.getHari());
    t.setRuangan(entity.getRuangan());
    t.setWaktuMulai(entity.getWaktuMulai());
    t.setWaktuSelesai(entity.getWaktuSelesai());
  }
}
