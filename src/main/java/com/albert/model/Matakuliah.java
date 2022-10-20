package com.albert.model;

public class Matakuliah {
	
	private Integer matakuliahId;
	private String namaMatakuliah;

	public Matakuliah(Integer matakuliahId, String namaMatakuliah) {
		super();
		this.matakuliahId = matakuliahId;
		this.namaMatakuliah = namaMatakuliah;
	}

	public Integer getMatakuliahId() {
		return matakuliahId;
	}

	public void setMatakuliahId(Integer matakuliahId) {
		this.matakuliahId = matakuliahId;
	}

	public String getNamaMatakuliah() {
		return namaMatakuliah;
	}

	public void setNamaMatakuliah(String namaMatakuliah) {
		this.namaMatakuliah = namaMatakuliah;
	}

}
