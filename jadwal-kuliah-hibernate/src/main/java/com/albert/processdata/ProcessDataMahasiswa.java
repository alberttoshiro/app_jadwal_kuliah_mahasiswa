package com.albert.processdata;

import java.util.UUID;
import com.albert.dao.MahasiswaDAO;
import com.albert.model.Mahasiswa;

public class ProcessDataMahasiswa extends ProcessData<MahasiswaDAO, Mahasiswa> {

  public ProcessDataMahasiswa(String folderName, String filePath, MahasiswaDAO DAO) {
    super(folderName, filePath, DAO);
  }

  @Override
  public Mahasiswa convertToU(String[] param) {
    Mahasiswa mahasiswa = new Mahasiswa();
    mahasiswa.setId(UUID.fromString(param[0]));
    mahasiswa.setNim(param[1]);
    mahasiswa.setNama(param[2]);
    return mahasiswa;
  }

}
