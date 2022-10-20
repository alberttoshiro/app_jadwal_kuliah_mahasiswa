package com.albert.dao;

import java.util.List;

import com.albert.model.Mahasiswa;

public interface MahasiswaDao {
	List<Mahasiswa> getMahasiswaByName(String name);
	List<Mahasiswa> getMahasiswaByNim(Integer nim);
}
