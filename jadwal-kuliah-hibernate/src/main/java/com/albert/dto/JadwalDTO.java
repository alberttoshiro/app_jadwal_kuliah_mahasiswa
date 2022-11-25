package com.albert.dto;

import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JadwalDTO {

  private MatakuliahDTO matakuliahDTO;
  private String hari;
  private String nomorRuangan;
  private LocalTime waktuMulai;
  private LocalTime waktuSelesai;

  public JadwalDTO() {

  }

  public JadwalDTO(MatakuliahDTO matakuliahDTO, String hari, String nomorRuangan,
      LocalTime waktuMulai, LocalTime waktuSelesai) {
    super();
    this.matakuliahDTO = matakuliahDTO;
    this.hari = hari;
    this.nomorRuangan = nomorRuangan;
    this.waktuMulai = waktuMulai;
    this.waktuSelesai = waktuSelesai;
  }

}
