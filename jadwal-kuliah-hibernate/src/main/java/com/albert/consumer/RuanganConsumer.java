package com.albert.consumer;

import java.io.IOException;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dao.RuanganDAO;
import com.albert.model.Ruangan;
import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class RuanganConsumer extends BaseConsumer {

  @Inject
  RuanganDAO ruanganDAO;

  @Override
  public void insert(String[] param) {
    Ruangan ruangan = new Ruangan();
    ruangan.setId(UUID.fromString(param[0]));
    ruangan.setNomorRuangan(param[1]);
    ruanganDAO.save(ruangan);
  }

  @ConsumeEvent(value = "processRuangan", blocking = true)
  public void processRuangan(String filePath) throws IOException {
    processFile(filePath, "master", "master-done");
  }
}
