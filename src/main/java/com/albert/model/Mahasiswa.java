package com.albert.model;

public class Mahasiswa {

	private Integer nim;
	private String nama;

	public Mahasiswa(Integer nim, String nama) {
		super();
		this.nim = nim;
		this.nama = nama;
	}

	public Integer getNim() {
		return nim;
	}

	public void setNim(Integer nim) {
		this.nim = nim;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}
