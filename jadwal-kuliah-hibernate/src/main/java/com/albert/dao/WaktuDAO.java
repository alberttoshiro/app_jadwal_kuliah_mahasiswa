package com.albert.dao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.albert.model.Waktu;

@ApplicationScoped
public class WaktuDAO extends BaseDAO<Waktu> {

  @Inject
  EntityManager entityManager;

  @PostConstruct
  public void init() {
    setEntityClass(Waktu.class);
    setEntityManager(entityManager);
  }

  @Override
  protected void updateEntity(Waktu t, Waktu entity) {
    t.setId(entity.getId());
    t.setKodeWaktu(entity.getKodeWaktu());
    t.setWaktuMulai(entity.getWaktuMulai());
    t.setWaktuSelesai(entity.getWaktuSelesai());
  }
}
