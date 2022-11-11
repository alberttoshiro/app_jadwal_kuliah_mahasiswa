package com.albert.dao;

import com.albert.model.Matakuliah;

public class MatakuliahDAO extends BaseDAO<Matakuliah> {

  public MatakuliahDAO() {
    super();
    this.setEntityClass(Matakuliah.class);
  }
}
