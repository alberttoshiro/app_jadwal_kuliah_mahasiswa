package com.albert.consumer;

import java.io.IOException;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dao.JadwalKuliahDAO;
import com.albert.model.JadwalKuliah;
import com.albert.util.AppUtil;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class JadwalKuliahConsumer extends BaseConsumer {

  @Inject
  JadwalKuliahDAO jadwalKuliahDAO;

  @Override
  public void insert(String[] param) {
    JadwalKuliah jadwalKuliah = new JadwalKuliah();
    jadwalKuliah.setId(UUID.fromString(param[0]));
    jadwalKuliah.setHari(param[1]);
    jadwalKuliah.setRuangan(param[2]);
    jadwalKuliah.setWaktuMulai(AppUtil.convertStringToLocalTime(param[3]));
    jadwalKuliah.setWaktuSelesai(AppUtil.convertStringToLocalTime(param[4]));
    jadwalKuliahDAO.save(jadwalKuliah);
  }

  @ConsumeEvent(value = "processJadwalKuliah", blocking = true)
  public void processJadwalKuliah(String filePath) throws IOException {
    processFile(filePath, "master", "master-done");
  }
}
