package com.albert.dao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.albert.model.Matakuliah;

@ApplicationScoped
public class MatakuliahDAO extends BaseDAO<Matakuliah> {

  @Inject
  EntityManager entityManager;

  @PostConstruct
  public void init() {
    setEntityClass(Matakuliah.class);
    setEntityManager(entityManager);
  }

  @Override
  public void updateEntity(Matakuliah t, Matakuliah entity) {
    t.setNamaMatakuliah(entity.getNamaMatakuliah());
  }
}
