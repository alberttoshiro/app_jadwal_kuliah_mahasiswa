package com.albert.dao;

import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.albert.model.Hari;
import com.albert.model.Ruangan;
import com.albert.model.RuanganWaktu;
import com.albert.model.Waktu;

@ApplicationScoped
public class RuanganWaktuDAO extends BaseDAO<RuanganWaktu> {

  @Inject
  EntityManager entityManager;

  public RuanganWaktu convertToRuanganWaktu(UUID ruanganId, UUID hariId, UUID waktuId) {
    RuanganWaktu ruanganWaktu = new RuanganWaktu();
    ruanganWaktu.setRuangan(entityManager.getReference(Ruangan.class, ruanganId));
    ruanganWaktu.setHari(entityManager.getReference(Hari.class, hariId));
    ruanganWaktu.setWaktu(entityManager.getReference(Waktu.class, waktuId));
    return ruanganWaktu;
  }

  @PostConstruct
  public void init() {
    setEntityClass(RuanganWaktu.class);
    setEntityManager(entityManager);
  }

  @Override
  public void updateEntity(RuanganWaktu t, RuanganWaktu entity) {
    t.setRuangan(entity.getRuangan());
    t.setHari(entity.getHari());
    t.setWaktu(entity.getWaktu());
  }
}
