package com.albert.dao;

import java.util.List;
import com.albert.database.PostgresDatabase;
import com.albert.model.Mahasiswa;
import org.hibernate.query.Query;

public class MahasiswaDAO extends BaseDAO<Mahasiswa> {

  public MahasiswaDAO(PostgresDatabase postgresDatabase) {
    super(postgresDatabase);
    this.setEntityClass(Mahasiswa.class);
  }

  public List<Mahasiswa> findByNama(String nama) {
    String stringQuery = "from Mahasiswa where nama like :nama";
    Query<Mahasiswa> query = createQuery(stringQuery);
    query.setParameter("nama", "%" + nama + "%");
    return query.list();
  }

  public List<Mahasiswa> findByNim(String nim) {
    String stringQuery = "from Mahasiswa where nim like :nim";
    Query<Mahasiswa> query = createQuery(stringQuery);
    query.setParameter("nim", "%" + nim + "%");
    return query.list();
  }
}
