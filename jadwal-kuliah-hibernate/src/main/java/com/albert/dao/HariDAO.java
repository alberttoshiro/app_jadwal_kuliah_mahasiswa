package com.albert.dao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.albert.model.Hari;

@ApplicationScoped
public class HariDAO extends BaseDAO<Hari> {

  @Inject
  EntityManager entityManager;

  @PostConstruct
  public void init() {
    setEntityClass(Hari.class);
    setEntityManager(entityManager);
  }

  @Override
  protected void updateEntity(Hari t, Hari entity) {
    t.setId(entity.getId());
    t.setKodeHari(entity.getKodeHari());
    t.setNamaHari(entity.getNamaHari());
  }

}
