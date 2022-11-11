package com.albert.dao;

import com.albert.model.JadwalKuliah;

public class JadwalKuliahDAO extends BaseDAO<JadwalKuliah> {

  public JadwalKuliahDAO() {
    super();
    this.setEntityClass(JadwalKuliah.class);
  }
}
