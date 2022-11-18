package com.albert.dao;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import com.albert.model.JadwalKuliah;
import com.albert.model.JadwalKuliahMahasiswa;
import com.albert.model.Mahasiswa;
import com.albert.model.Matakuliah;

@ApplicationScoped
public class JadwalKuliahMahasiswaDAO extends BaseDAO<JadwalKuliahMahasiswa> {

  @Inject
  EntityManager entityManager;

  public JadwalKuliahMahasiswa convertToJadwalKuliahMahasiswa(UUID mahasiswaId, UUID matakuliahId,
      UUID jadwalKuliahId) {
    JadwalKuliahMahasiswa jadwalKuliahMahasiswa = new JadwalKuliahMahasiswa();
    jadwalKuliahMahasiswa.setMahasiswa(entityManager.getReference(Mahasiswa.class, mahasiswaId));
    jadwalKuliahMahasiswa.setMatakuliah(entityManager.getReference(Matakuliah.class, matakuliahId));
    jadwalKuliahMahasiswa
        .setJadwalKuliah(entityManager.getReference(JadwalKuliah.class, jadwalKuliahId));
    return jadwalKuliahMahasiswa;
  }

  @Transactional
  @SuppressWarnings("unchecked")
  public List<JadwalKuliahMahasiswa> findByMahasiswa(Mahasiswa mahasiswa) {
    String stringQuery =
        "SELECT jkm from JadwalKuliahMahasiswa jkm WHERE jkm.mahasiswa.id = :mahasiswaId";
    Query query = entityManager.createQuery(stringQuery, getEntityClass());
    query.setParameter("mahasiswaId", mahasiswa.getId());
    List<JadwalKuliahMahasiswa> list = query.getResultList();
    Collections.sort(list);
    return list;
  }

  @PostConstruct
  public void init() {
    setEntityClass(JadwalKuliahMahasiswa.class);
    setEntityManager(entityManager);
  }

  @Transactional
  @Override
  public void updateEntity(JadwalKuliahMahasiswa t, JadwalKuliahMahasiswa entity) {
    t.setMahasiswa(entity.getMahasiswa());
    t.setMatakuliah(entity.getMatakuliah());
    t.setJadwalKuliah(entity.getJadwalKuliah());
  }
}
