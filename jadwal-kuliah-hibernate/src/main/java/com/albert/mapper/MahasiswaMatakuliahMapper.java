package com.albert.mapper;

import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.albert.dto.MahasiswaMatakuliahDTO;
import com.albert.model.Mahasiswa;

@ApplicationScoped
public class MahasiswaMatakuliahMapper {

  @Inject
  MahasiswaMapper mahasiswaMapper;

  @Inject
  MatakuliahMapper matakuliahMapper;

  public MahasiswaMatakuliahDTO toMahasiswaMatakuliahDTO(Mahasiswa mahasiswa) {
    return new MahasiswaMatakuliahDTO(mahasiswaMapper.toMahasiswaDTO(mahasiswa),
        mahasiswa.getListMatakuliah().stream().map(t -> matakuliahMapper.toMatakuliahDTO(t))
            .collect(Collectors.toList()));
  }
}
