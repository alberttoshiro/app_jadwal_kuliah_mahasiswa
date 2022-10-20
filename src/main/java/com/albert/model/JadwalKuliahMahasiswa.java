package com.albert.model;

public class JadwalKuliahMahasiswa {

	private Integer nim;
	private String hari;
	private String namaMatakuliah;
	private String ruangan;
	private String jam;

	public JadwalKuliahMahasiswa(Integer nim, String hari, String namaMatakuliah, String ruangan, String jam) {
		super();
		this.nim = nim;
		this.hari = hari;
		this.namaMatakuliah = namaMatakuliah;
		this.ruangan = ruangan;
		this.jam = jam;
	}

	public Integer getNim() {
		return nim;
	}

	public void setNim(Integer nim) {
		this.nim = nim;
	}

	public String getHari() {
		return hari;
	}

	public void setHari(String hari) {
		this.hari = hari;
	}

	public String getNamaMatakuliah() {
		return namaMatakuliah;
	}

	public void setNamaMatakuliah(String namaMatakuliah) {
		this.namaMatakuliah = namaMatakuliah;
	}

	public String getRuangan() {
		return ruangan;
	}

	public void setRuangan(String ruangan) {
		this.ruangan = ruangan;
	}

	public String getJam() {
		return jam;
	}

	public void setJam(String jam) {
		this.jam = jam;
	}

}
