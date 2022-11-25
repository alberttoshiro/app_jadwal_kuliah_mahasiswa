package com.albert.mapper;

import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dto.JadwalKuliahDTO;
import com.albert.model.JadwalKuliah;
import com.albert.model.Mahasiswa;

@ApplicationScoped
public class JadwalKuliahMapper {

  @Inject
  MahasiswaMapper mahasiswaMapper;

  @Inject
  JadwalMapper jadwalMapper;

  public JadwalKuliahDTO toJadwalKuliahDTO(Mahasiswa mahasiswa,
      List<JadwalKuliah> listJadwalKuliah) {
    JadwalKuliahDTO jadwalKuliahDTO = new JadwalKuliahDTO();
    if (mahasiswa == null) {
      return jadwalKuliahDTO;
    }
    jadwalKuliahDTO.setMahasiswaDTO(mahasiswaMapper.toMahasiswaDTO(mahasiswa));
    if (listJadwalKuliah == null || listJadwalKuliah.size() == 0) {
      return jadwalKuliahDTO;
    }
    jadwalKuliahDTO.setListJadwalDTO(listJadwalKuliah.stream().map(t -> jadwalMapper.toJadwalDTO(t))
        .collect(Collectors.toList()));
    return jadwalKuliahDTO;
  }

}
