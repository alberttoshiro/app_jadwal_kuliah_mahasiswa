package com.albert.processdata;

import java.util.UUID;
import com.albert.dao.JadwalKuliahMahasiswaDAO;
import com.albert.model.JadwalKuliahMahasiswa;

public class ProcessDataJadwalKuliahMahasiswa
    extends ProcessData<JadwalKuliahMahasiswaDAO, JadwalKuliahMahasiswa> {

  public ProcessDataJadwalKuliahMahasiswa(String folderName, String filePath,
      JadwalKuliahMahasiswaDAO DAO) {
    super(folderName, filePath, DAO);
  }

  @Override
  public JadwalKuliahMahasiswa convertToU(String[] param) {
    UUID id = UUID.fromString(param[0]);
    UUID mahasiswaId = UUID.fromString(param[1]);
    UUID matakuliahId = UUID.fromString(param[2]);
    UUID jadwalKuliahId = UUID.fromString(param[3]);
    JadwalKuliahMahasiswa jadwalKuliahMahasiswa =
        DAO.convertToJadwalKuliahMahasiswa(mahasiswaId, matakuliahId, jadwalKuliahId);
    jadwalKuliahMahasiswa.setId(id);
    return jadwalKuliahMahasiswa;
  }

}
