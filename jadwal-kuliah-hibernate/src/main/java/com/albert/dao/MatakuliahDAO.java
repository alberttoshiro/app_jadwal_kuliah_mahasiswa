package com.albert.dao;

import javax.enterprise.context.ApplicationScoped;
import com.albert.model.Matakuliah;

@ApplicationScoped
public class MatakuliahDAO extends BaseDAO<Matakuliah> {

  public MatakuliahDAO() {
    super();
    this.setEntityClass(Matakuliah.class);
  }

  @Override
  public void updateEntity(Matakuliah t, Matakuliah entity) {
    t.setNamaMatakuliah(entity.getNamaMatakuliah());
  }
}
