package com.albert.model;

public class JadwalKuliahMahasiswa {

	private Integer nim;
	private Integer matakuliahId;
	private Integer jadwalKuliahId;

	public JadwalKuliahMahasiswa(Integer nim, Integer matakuliahId, Integer jadwalKuliahId) {
		super();
		this.nim = nim;
		this.matakuliahId = matakuliahId;
		this.jadwalKuliahId = jadwalKuliahId;
	}

	public Integer getNim() {
		return nim;
	}

	public void setNim(Integer nim) {
		this.nim = nim;
	}

	public Integer getMatakuliahId() {
		return matakuliahId;
	}

	public void setMatakuliahId(Integer matakuliahId) {
		this.matakuliahId = matakuliahId;
	}

	public Integer getJadwalKuliahId() {
		return jadwalKuliahId;
	}

	public void setJadwalKuliahId(Integer jadwalKuliahId) {
		this.jadwalKuliahId = jadwalKuliahId;
	}

}
