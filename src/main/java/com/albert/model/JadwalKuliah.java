package com.albert.model;

public class JadwalKuliah {

	private Integer jadwalKuliahId;
	private String ruangan;
	private String hari;
	private String jam;

	public JadwalKuliah(Integer jadwalKuliahId, String ruangan, String hari, String jam) {
		super();
		this.jadwalKuliahId = jadwalKuliahId;
		this.ruangan = ruangan;
		this.hari = hari;
		this.jam = jam;
	}

	public Integer getJadwalKuliahId() {
		return jadwalKuliahId;
	}

	public void setJadwalKuliahId(Integer jadwalKuliahId) {
		this.jadwalKuliahId = jadwalKuliahId;
	}

	public String getRuangan() {
		return ruangan;
	}

	public void setRuangan(String ruangan) {
		this.ruangan = ruangan;
	}

	public String getHari() {
		return hari;
	}

	public void setHari(String hari) {
		this.hari = hari;
	}

	public String getJam() {
		return jam;
	}

	public void setJam(String jam) {
		this.jam = jam;
	}

}
