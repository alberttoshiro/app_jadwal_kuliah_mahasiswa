package com.albert.processdata;

import java.util.UUID;
import com.albert.dao.JadwalKuliahDAO;
import com.albert.model.JadwalKuliah;
import com.albert.util.AppUtil;

public class ProcessDataJadwalKuliah extends ProcessData<JadwalKuliahDAO, JadwalKuliah> {

  public ProcessDataJadwalKuliah(String folderName, String filePath, JadwalKuliahDAO DAO) {
    super(folderName, filePath, DAO);
  }

  @Override
  public JadwalKuliah convertToU(String[] param) {
    JadwalKuliah jadwalKuliah = new JadwalKuliah();
    jadwalKuliah.setId(UUID.fromString(param[0]));
    jadwalKuliah.setHari(param[1]);
    jadwalKuliah.setRuangan(param[2]);
    jadwalKuliah.setWaktuMulai(AppUtil.convertStringToLocalTime(param[3]));
    jadwalKuliah.setWaktuSelesai(AppUtil.convertStringToLocalTime(param[4]));
    return jadwalKuliah;
  }


}
