package com.albert.jadwalkuliah;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.albert.dao.JadwalKuliahMahasiswaDaoImpl;
import com.albert.dao.MahasiswaDaoImpl;
import com.albert.model.JadwalKuliahMahasiswa;
import com.albert.model.Mahasiswa;
import com.albert.postgresql.JDBCPostgreSQLConnect;

public class Search {

	public static void printSeparator(char c) {
		for(int i = 0 ; i < 90 ; i++) {
			System.out.print(c);
		}
		System.out.println();
	}
	
	public static List<Mahasiswa> getMahasiswa(String searchBy) {
		MahasiswaDaoImpl mahasiswaDaoImpl = new MahasiswaDaoImpl();
		if(searchBy.startsWith("nama:")) {
			searchBy = searchBy.replace("nama:", "");
			return mahasiswaDaoImpl.getMahasiswaByName(searchBy);
		} else if(searchBy.startsWith("nim:")) {
			searchBy = searchBy.replace("nim:", "");
			try {
				return mahasiswaDaoImpl.getMahasiswaByNim(Integer.valueOf(searchBy));
			} catch (Exception e) {
				System.out.println("Nim must be digit only...");
				return null;
			}		
		} else {
			System.out.println("Please use prefix nama: or nim: followed by your name or nim");
			return null;
		}
	}
	
	public static void search(String searchBy) {
		List<Mahasiswa> listMahasiswa = getMahasiswa(searchBy);
			
		if(listMahasiswa == null) {
			return;
		}
		if(listMahasiswa.isEmpty()) {
			System.out.println("Mahasiswa not found...");
			return;
		}
		
		JadwalKuliahMahasiswaDaoImpl jkmDaoImpl = new JadwalKuliahMahasiswaDaoImpl();
		for (Mahasiswa mhs : listMahasiswa) {
			List<JadwalKuliahMahasiswa> jkmList = jkmDaoImpl.getJadwalKuliahMahasiswaByNim(mhs.getNim());
			printSeparator('=');
			System.out.println(mhs.getNama() + ", nim: " + mhs.getNim());
			printSeparator('=');
			if(jkmList == null || jkmList.isEmpty()) {
				System.out.println("You have no schedule for now...");
				printSeparator('-');
				continue;		
			}
			for (JadwalKuliahMahasiswa jkm : jkmList) {
				System.out.printf("%-10s | %-40s | %-10s | %-11s |%n", jkm.getHari(), jkm.getNamaMatakuliah(), jkm.getRuangan(), jkm.getJam());
			}
			printSeparator('-');
		}
	}
	
	public static void main(String[] args) {
		String search = "";
		if(args.length < 1) {
			search = "nama:u";
//			return;
		} else {
			search = args[0];
		}
		search = search.toLowerCase();

		search(search);
	}
}
