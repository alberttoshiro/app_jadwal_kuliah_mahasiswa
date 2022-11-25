package com.albert.dao;

import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import com.albert.model.Mahasiswa;
import com.albert.model.Matakuliah;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MatakuliahDAO extends BaseDAO<Matakuliah> {

  @Inject
  EntityManager entityManager;

  @Inject
  Logger log;

  @Transactional
  public void addMatakuliahToMahasiswa(UUID mahasiswaId, UUID matakuliahId) {
    Mahasiswa mahasiswa = entityManager.getReference(Mahasiswa.class, mahasiswaId);
    mahasiswa.getListMatakuliah().add(entityManager.getReference(Matakuliah.class, matakuliahId));
    if (mahasiswa.getListMatakuliah() == null) {
      log.info("mahasiswa listmatakuliah null");
    } else {
      for (Matakuliah matakuliah : mahasiswa.getListMatakuliah()) {
        log.info(mahasiswa.getNama() + " : " + matakuliah.getNamaMatakuliah());
      }
    }
  }

  @PostConstruct
  public void init() {
    setEntityClass(Matakuliah.class);
    setEntityManager(entityManager);
  }

  @Override
  public void updateEntity(Matakuliah t, Matakuliah entity) {
    t.setNamaMatakuliah(entity.getNamaMatakuliah());
  }
}
