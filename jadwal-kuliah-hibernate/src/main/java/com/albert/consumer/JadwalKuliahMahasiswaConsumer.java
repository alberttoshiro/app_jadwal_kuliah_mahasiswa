package com.albert.consumer;

import java.io.IOException;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dao.JadwalKuliahMahasiswaDAO;
import com.albert.model.JadwalKuliahMahasiswa;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class JadwalKuliahMahasiswaConsumer extends BaseConsumer {

  @Inject
  JadwalKuliahMahasiswaDAO jadwalKuliahMahasiswaDAO;

  @Override
  public void insert(String[] param) {
    UUID id = UUID.fromString(param[0]);
    UUID mahasiswaId = UUID.fromString(param[1]);
    UUID matakuliahId = UUID.fromString(param[2]);
    UUID jadwalKuliahId = UUID.fromString(param[3]);
    JadwalKuliahMahasiswa jadwalKuliahMahasiswa = jadwalKuliahMahasiswaDAO
        .convertToJadwalKuliahMahasiswa(mahasiswaId, matakuliahId, jadwalKuliahId);
    jadwalKuliahMahasiswa.setId(id);
    jadwalKuliahMahasiswaDAO.save(jadwalKuliahMahasiswa);
  }

  @ConsumeEvent(value = "processJadwalKuliahMahasiswa", blocking = true)
  public void processJadwalKuliahMahasiswa(String filePath) throws IOException {
    processFile(filePath, "compose", "compose-done");
  }
}
