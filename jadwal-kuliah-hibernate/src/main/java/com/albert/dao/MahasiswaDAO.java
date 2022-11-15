package com.albert.dao;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;
import com.albert.model.Mahasiswa;

@ApplicationScoped
public class MahasiswaDAO extends BaseDAO<Mahasiswa> {

  public MahasiswaDAO() {
    super();
    this.setEntityClass(Mahasiswa.class);
  }

  @Transactional
  @SuppressWarnings("unchecked")
  public List<Mahasiswa> findByNama(String nama) {
    String stringQuery =
        String.format("SELECT m from Mahasiswa m WHERE m.nama LIKE '%%%s%%'", nama);
    Query query = entityManager.createQuery(stringQuery, entityClass);
    return query.getResultList();
  }

  @Transactional
  @SuppressWarnings("unchecked")
  public List<Mahasiswa> findByNim(String nim) {
    String stringQuery = String.format("SELECT m from Mahasiswa m WHERE m.nim LIKE '%%%s%%'", nim);
    Query query = entityManager.createQuery(stringQuery, entityClass);
    return query.getResultList();
  }

  @Override
  public void updateEntity(Mahasiswa t, Mahasiswa entity) {
    t.setNim(entity.getNim());
    t.setNama(entity.getNama());
  }

}
