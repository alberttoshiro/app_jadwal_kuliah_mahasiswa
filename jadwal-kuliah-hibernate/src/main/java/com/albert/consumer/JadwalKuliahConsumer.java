package com.albert.consumer;

import java.io.IOException;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dao.JadwalKuliahDAO;
import com.albert.dao.MatakuliahDAO;
import com.albert.model.JadwalKuliah;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class JadwalKuliahConsumer extends BaseConsumer {

  @Inject
  JadwalKuliahDAO jadwalKuliahDAO;

  @Inject
  MatakuliahDAO matakuliahDAO;

  @Override
  public void insert(String[] param) {
    UUID id = UUID.fromString(param[0]);
    UUID mahasiswaId = UUID.fromString(param[1]);
    UUID matakuliahId = UUID.fromString(param[2]);
    UUID ruanganWaktuId = UUID.fromString(param[3]);
    JadwalKuliah jadwalKuliah =
        jadwalKuliahDAO.convertToJadwalKuliah(mahasiswaId, matakuliahId, ruanganWaktuId);
    jadwalKuliah.setId(id);
    jadwalKuliahDAO.save(jadwalKuliah);
    matakuliahDAO.addMatakuliahToMahasiswa(mahasiswaId, matakuliahId);
  }

  @ConsumeEvent(value = "processJadwalKuliah", blocking = true)
  public void processJadwalKuliah(String filePath) throws IOException {
    processFile(filePath, "compose", "compose-done");
  }
}
