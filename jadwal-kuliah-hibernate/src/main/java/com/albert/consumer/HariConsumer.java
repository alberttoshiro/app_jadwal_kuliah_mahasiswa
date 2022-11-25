package com.albert.consumer;

import java.io.IOException;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dao.HariDAO;
import com.albert.model.Hari;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class HariConsumer extends BaseConsumer {

  @Inject
  HariDAO hariDAO;

  @Override
  public void insert(String[] param) {
    Hari hari = new Hari();
    hari.setId(UUID.fromString(param[0]));
    hari.setKodeHari(Integer.valueOf(param[1]));
    hari.setNamaHari(param[2]);
    hariDAO.save(hari);
  }

  @ConsumeEvent(value = "processHari", blocking = true)
  public void processHari(String filePath) throws IOException {
    processFile(filePath, "master", "master-done");
  }
}
