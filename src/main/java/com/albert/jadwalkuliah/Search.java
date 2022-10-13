package com.albert.jadwalkuliah;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		List<Mahasiswa> listMahasiswa = new ArrayList<>();
		Connection con = JDBCPostgreSQLConnect.getConnection();
		String searchSQL = "";
		if(searchBy.startsWith("nama")) {
			searchBy = searchBy.replace("nama:", "");
			searchSQL = "SELECT * FROM mahasiswa WHERE nama ILIKE '%" + searchBy + "%'";
		} else {
			searchBy = searchBy.replace("nim:", "");
			searchSQL = "SELECT * FROM mahasiswa WHERE nim = " + searchBy;
		}
		PreparedStatement st;
		
		try {	
			st = con.prepareStatement(searchSQL);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Mahasiswa mhs = new Mahasiswa(rs.getInt(1), rs.getString(2));
				listMahasiswa.add(mhs);
			}
			
			for (Mahasiswa mhs : listMahasiswa) {
				String sql = "SELECT jadwal_kuliah.hari, matakuliah.nama_matakuliah, jadwal_kuliah.ruangan, jadwal_kuliah.jam "
						+ "FROM jadwal_kuliah_mahasiswa "
						+ "JOIN mahasiswa ON mahasiswa.nim = jadwal_kuliah_mahasiswa.nim "
						+ "JOIN jadwal_kuliah ON jadwal_kuliah.jadwal_kuliah_id = jadwal_kuliah_mahasiswa.jadwal_kuliah_id "
						+ "JOIN matakuliah ON matakuliah.matakuliah_id = jadwal_kuliah_mahasiswa.matakuliah_id "
						+ "WHERE jadwal_kuliah_mahasiswa.nim = ? "
						+ "ORDER BY jadwal_kuliah.jadwal_kuliah_id";
				st = con.prepareStatement(sql);
				st.setInt(1, mhs.getNim());
				rs = st.executeQuery();
				printSeparator('=');
				System.out.println(mhs.getNama() + ", nim: " + mhs.getNim());
				printSeparator('=');
				while(rs.next()) {
					System.out.printf("%-10s | %-40s | %-10s | %-11s |%n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
					
				}
				printSeparator('-');
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

//		search(search);
		System.out.println("This is a search class searching: " + search);
	}
}
