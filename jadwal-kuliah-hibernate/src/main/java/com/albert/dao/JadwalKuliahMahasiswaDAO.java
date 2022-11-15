package com.albert.dao;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import com.albert.model.JadwalKuliahMahasiswa;
import com.albert.model.Mahasiswa;

@ApplicationScoped
public class JadwalKuliahMahasiswaDAO extends BaseDAO<JadwalKuliahMahasiswa> {

  @Inject
  private MahasiswaDAO mahasiswaDAO;

  @Inject
  private MatakuliahDAO matakuliahDAO;

  @Inject
  private JadwalKuliahDAO jadwalKuliahDAO;

  public JadwalKuliahMahasiswaDAO() {
    super();
    this.setEntityClass(JadwalKuliahMahasiswa.class);
  }

  public JadwalKuliahMahasiswa convertToJadwalKuliahMahasiswa(UUID mahasiswaId, UUID matakuliahId,
      UUID jadwalKuliahId) {
    JadwalKuliahMahasiswa jadwalKuliahMahasiswa = new JadwalKuliahMahasiswa();
    jadwalKuliahMahasiswa.setMahasiswa(mahasiswaDAO.findById(mahasiswaId));
    jadwalKuliahMahasiswa.setMatakuliah(matakuliahDAO.findById(matakuliahId));
    jadwalKuliahMahasiswa.setJadwalKuliah(jadwalKuliahDAO.findById(jadwalKuliahId));
    return jadwalKuliahMahasiswa;
  }

  @Transactional
  @SuppressWarnings("unchecked")
  public List<JadwalKuliahMahasiswa> findByMahasiswa(Mahasiswa mahasiswa) {
    String stringQuery = String.format(
        "SELECT jkm from JadwalKuliahMahasiswa jkm WHERE jkm.mahasiswa_id = ", mahasiswa.getId());
    Query query = entityManager.createQuery(stringQuery, entityClass);
    List<JadwalKuliahMahasiswa> list = query.getResultList();
    Collections.sort(list);
    return list;
  }

  @Transactional
  @Override
  public void updateEntity(JadwalKuliahMahasiswa t, JadwalKuliahMahasiswa entity) {
    t.setMahasiswa(entity.getMahasiswa());
    t.setMatakuliah(entity.getMatakuliah());
    t.setJadwalKuliah(entity.getJadwalKuliah());
  }
}
