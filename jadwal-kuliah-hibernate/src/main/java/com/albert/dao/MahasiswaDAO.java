package com.albert.dao;

import java.util.List;
import com.albert.database.PostgresDatabase;
import com.albert.model.Mahasiswa;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class MahasiswaDAO extends BaseDAO<Mahasiswa> {

  public MahasiswaDAO() {
    super();
    this.setEntityClass(Mahasiswa.class);
  }

  public List<Mahasiswa> findByNama(String nama) {
    String stringQuery = "from Mahasiswa where nama like :nama";
    Session session = PostgresDatabase.getSession();
    Query<Mahasiswa> query = createQuery(stringQuery, session);
    query.setParameter("nama", "%" + nama + "%");
    List<Mahasiswa> list = query.list();
    session.close();
    return list;
  }

  public List<Mahasiswa> findByNim(String nim) {
    String stringQuery = "from Mahasiswa where nim like :nim";
    Session session = PostgresDatabase.getSession();
    Query<Mahasiswa> query = createQuery(stringQuery, session);
    query.setParameter("nim", "%" + nim + "%");
    List<Mahasiswa> list = query.list();
    session.close();
    return list;
  }
}
