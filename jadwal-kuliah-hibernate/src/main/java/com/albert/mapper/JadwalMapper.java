package com.albert.mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dto.JadwalDTO;
import com.albert.model.JadwalKuliah;
import com.albert.model.RuanganWaktu;

@ApplicationScoped
public class JadwalMapper {

  @Inject
  MatakuliahMapper matakuliahMapper;

  public JadwalDTO toJadwalDTO(JadwalKuliah jadwalKuliah) {
    RuanganWaktu ruanganWaktu = jadwalKuliah.getRuanganWaktu();
    return new JadwalDTO(matakuliahMapper.toMatakuliahDTO(jadwalKuliah.getMatakuliah()),
        ruanganWaktu.getHari().getNamaHari(), ruanganWaktu.getRuangan().getNomorRuangan(),
        ruanganWaktu.getWaktu().getWaktuMulai(), ruanganWaktu.getWaktu().getWaktuMulai());
  }

}
