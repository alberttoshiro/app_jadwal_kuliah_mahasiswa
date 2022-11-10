package com.albert.dao;

import java.util.List;
import java.util.UUID;
import com.albert.database.PostgresDatabase;
import com.albert.model.JadwalKuliah;
import com.albert.model.JadwalKuliahMahasiswa;
import com.albert.model.Mahasiswa;
import com.albert.model.Matakuliah;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class JadwalKuliahMahasiswaDAO extends BaseDAO<JadwalKuliahMahasiswa> {

  private MahasiswaDAO mahasiswaDAO;
  private MatakuliahDAO matakuliahDAO;
  private JadwalKuliahDAO jadwalKuliahDAO;

  public JadwalKuliahMahasiswaDAO(PostgresDatabase postgresDatabase) {
    super(postgresDatabase);
    this.setEntityClass(JadwalKuliahMahasiswa.class);
  }

  public JadwalKuliahMahasiswa convertToJadwalKuliahMahasiswa(UUID mahasiswaId, UUID matakuliahId,
      UUID jadwalKuliahId) {
    if (mahasiswaDAO == null) {
      mahasiswaDAO = new MahasiswaDAO(postgresDatabase);
    }
    if (matakuliahDAO == null) {
      matakuliahDAO = new MatakuliahDAO(postgresDatabase);
    }
    if (jadwalKuliahDAO == null) {
      jadwalKuliahDAO = new JadwalKuliahDAO(postgresDatabase);
    }
    Mahasiswa mahasiswa = mahasiswaDAO.findById(mahasiswaId);
    Matakuliah matakuliah = matakuliahDAO.findById(matakuliahId);
    JadwalKuliah jadwalKuliah = jadwalKuliahDAO.findById(jadwalKuliahId);
    JadwalKuliahMahasiswa jadwalKuliahMahasiswa = new JadwalKuliahMahasiswa();
    jadwalKuliahMahasiswa.setMahasiswa(mahasiswa);
    jadwalKuliahMahasiswa.setMatakuliah(matakuliah);
    jadwalKuliahMahasiswa.setJadwalKuliah(jadwalKuliah);
    return jadwalKuliahMahasiswa;
  }

  public List<JadwalKuliahMahasiswa> findByMahasiswa(Mahasiswa mahasiswa) {
    Session session = postgresDatabase.getSession();
    String stringQuery = "from JadwalKuliahMahasiswa where mahasiswa_id = :mahasiswaId";
    Query<JadwalKuliahMahasiswa> query = createQuery(stringQuery, session);
    query.setParameter("mahasiswaId", mahasiswa.getId());
    List<JadwalKuliahMahasiswa> list = query.list();
    session.close();
    return list;
  }
}
