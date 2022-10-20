package com.albert.dao;

import java.util.List;

import com.albert.model.JadwalKuliahMahasiswa;

public interface JadwalKuliahMahasiswaDao {
	List<JadwalKuliahMahasiswa> getJadwalKuliahMahasiswaByNim(Integer nim);
}
