package com.albert.consumer;

import java.io.IOException;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dao.RuanganWaktuDAO;
import com.albert.model.RuanganWaktu;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class RuanganWaktuConsumer extends BaseConsumer {

  @Inject
  RuanganWaktuDAO ruanganWaktuDAO;

  @Override
  public void insert(String[] param) {
    UUID id = UUID.fromString(param[0]);
    UUID ruanganId = UUID.fromString(param[1]);
    UUID hariId = UUID.fromString(param[2]);
    UUID waktuId = UUID.fromString(param[3]);
    RuanganWaktu ruanganWaktu = ruanganWaktuDAO.convertToRuanganWaktu(ruanganId, hariId, waktuId);
    ruanganWaktu.setId(id);
    ruanganWaktuDAO.save(ruanganWaktu);
  }

  @ConsumeEvent(value = "processRuanganWaktu", blocking = true)
  public void processHari(String filePath) throws IOException {
    processFile(filePath, "compose", "compose-done");
  }
}
