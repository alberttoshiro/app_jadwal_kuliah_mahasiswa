package com.albert.consumer;

import java.io.IOException;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dao.WaktuDAO;
import com.albert.model.Waktu;
import com.albert.util.AppUtil;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class WaktuConsumer extends BaseConsumer {

  @Inject
  WaktuDAO waktuDAO;

  @Override
  public void insert(String[] param) {
    Waktu waktu = new Waktu();
    waktu.setId(UUID.fromString(param[0]));
    waktu.setKodeWaktu(Integer.valueOf(param[1]));
    waktu.setWaktuMulai(AppUtil.convertStringToLocalTime(param[2]));
    waktu.setWaktuSelesai(AppUtil.convertStringToLocalTime(param[3]));
    waktuDAO.save(waktu);
  }

  @ConsumeEvent(value = "processWaktu", blocking = true)
  public void processHari(String filePath) throws IOException {
    processFile(filePath, "master", "master-done");
  }
}
