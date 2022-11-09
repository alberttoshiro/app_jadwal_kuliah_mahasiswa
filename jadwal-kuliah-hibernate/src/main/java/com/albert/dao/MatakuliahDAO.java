package com.albert.dao;

import com.albert.database.PostgresDatabase;
import com.albert.model.Matakuliah;

public class MatakuliahDAO extends BaseDAO<Matakuliah> {

  public MatakuliahDAO(PostgresDatabase postgresDatabase) {
    super(postgresDatabase);
    this.setEntityClass(Matakuliah.class);
  }
}
