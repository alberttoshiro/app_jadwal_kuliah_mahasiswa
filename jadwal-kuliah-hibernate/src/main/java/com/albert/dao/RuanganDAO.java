package com.albert.dao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.albert.model.Ruangan;

@ApplicationScoped
public class RuanganDAO extends BaseDAO<Ruangan> {

  @Inject
  EntityManager entityManager;

  @PostConstruct
  public void init() {
    setEntityClass(Ruangan.class);
    setEntityManager(entityManager);
  }

  @Override
  public void updateEntity(Ruangan t, Ruangan entity) {
    t.setId(entity.getId());
    t.setNomorRuangan(entity.getNomorRuangan());
  }
}
