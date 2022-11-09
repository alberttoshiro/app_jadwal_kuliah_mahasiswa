package com.albert.dao;

import com.albert.database.PostgresDatabase;
import com.albert.model.JadwalKuliah;

public class JadwalKuliahDAO extends BaseDAO<JadwalKuliah> {

  public JadwalKuliahDAO(PostgresDatabase postgresDatabase) {
    super(postgresDatabase);
    this.setEntityClass(JadwalKuliah.class);
  }
}
