package com.albert.dao;

import java.util.List;
import com.albert.database.PostgresDatabase;
import com.albert.model.JadwalKuliahMahasiswa;
import com.albert.model.Mahasiswa;
import org.hibernate.query.Query;

public class JadwalKuliahMahasiswaDAO extends BaseDAO<JadwalKuliahMahasiswa> {

  public JadwalKuliahMahasiswaDAO(PostgresDatabase postgresDatabase) {
    super(postgresDatabase);
    this.setEntityClass(JadwalKuliahMahasiswa.class);
  }

  public List<JadwalKuliahMahasiswa> findByMahasiswa(Mahasiswa mahasiswa) {
    String stringQuery = "from JadwalKuliahMahasiswa where mahasiswa_id = :mahasiswaId";
    Query<JadwalKuliahMahasiswa> query = createQuery(stringQuery);
    query.setParameter("mahasiswaId", mahasiswa.getId());
    return query.list();
  }
}
