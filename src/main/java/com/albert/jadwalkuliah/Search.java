package com.albert.jadwalkuliah;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public static void search(String searchBy) {
		JDBCPostgreSQLConnect conDB = new JDBCPostgreSQLConnect();
		List<Mahasiswa> listMahasiswa = conDB.getMahasiswa(searchBy);
			
		for (Mahasiswa mhs : listMahasiswa) {
			List<JadwalKuliahMahasiswa> jkmList = conDB.getJadwalKuliahMahasiswa(mhs.getNim());
			printSeparator('=');
			System.out.println(mhs.getNama() + ", nim: " + mhs.getNim());
			printSeparator('=');
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
		System.out.println("This is a search class searching: " + search);
	}
}
