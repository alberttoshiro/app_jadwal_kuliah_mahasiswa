package com.albert.dao;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import com.albert.model.Mahasiswa;
import io.quarkus.logging.Log;

@ApplicationScoped
public class MahasiswaDAO extends BaseDAO<Mahasiswa> {

  @Inject
  EntityManager entityManager;

  @Transactional
  @SuppressWarnings("unchecked")
  public List<Mahasiswa> findByNama(String nama) {
    nama = nama.toLowerCase();
    String stringQuery =
        String.format("SELECT m from Mahasiswa m WHERE LOWER(m.nama) LIKE '%%%s%%'", nama);
    Query query = entityManager.createQuery(stringQuery, getEntityClass());
    return query.getResultList();
  }

  @Transactional
  @SuppressWarnings("unchecked")
  public List<Mahasiswa> findByNim(String nim) {
    nim = nim.toLowerCase();
    if (entityManager == null) {
      Log.info("ENTITY MANAGER NULL");
      throw new IllegalArgumentException("ENTITY MANAGER NULL");
    }
    String stringQuery =
        String.format("SELECT m from Mahasiswa m WHERE LOWER(m.nim) LIKE '%%%s%%'", nim);
    Query query = null;
    query = entityManager.createQuery(stringQuery, getEntityClass());

    return query.getResultList();
  }

  @PostConstruct
  public void init() {
    setEntityClass(Mahasiswa.class);
    setEntityManager(entityManager);
  }

  @Override
  public void updateEntity(Mahasiswa t, Mahasiswa entity) {
    t.setNim(entity.getNim());
    t.setNama(entity.getNama());
  }

}
