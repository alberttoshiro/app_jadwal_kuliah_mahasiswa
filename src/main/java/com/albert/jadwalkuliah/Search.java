package com.albert.jadwalkuliah;

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
	
	public static List<Mahasiswa> getMahasiswa(String searchBy, JDBCPostgreSQLConnect con) {
		List<Mahasiswa> listMahasiswa = new ArrayList<>();
		String searchSQL = "";
		if(searchBy.startsWith("nama:")) {
			searchBy = searchBy.replace("nama:", "");
			searchSQL = "SELECT * FROM mahasiswa WHERE nama ILIKE '%" + searchBy + "%'";
		} else if(searchBy.startsWith("nim:")) {
			searchBy = searchBy.replace("nim:", "");
			searchSQL = "SELECT * FROM mahasiswa WHERE nim = " + searchBy;
		} else {
			System.out.println("Please use prefix nama: or nim: followed by your name or nim");
			return null;
		}
		
		try (PreparedStatement st = con.prepareQuery(searchSQL, null, null);
				ResultSet rs = st.executeQuery()) {
			while(rs.next()) {
				Mahasiswa mhs = new Mahasiswa(rs.getInt(1), rs.getString(2));
				listMahasiswa.add(mhs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			con.closeConnection();
		}
		return listMahasiswa;
	}
	
	public static List<JadwalKuliahMahasiswa> getJadwalKuliahMahasiswa(Integer nim, JDBCPostgreSQLConnect con) {			
		List<JadwalKuliahMahasiswa> jkmList = new ArrayList<>();
		
		String query = "SELECT jadwal_kuliah.hari, matakuliah.nama_matakuliah, jadwal_kuliah.ruangan, jadwal_kuliah.jam "
				+ "FROM jadwal_kuliah_mahasiswa "
				+ "JOIN mahasiswa ON mahasiswa.nim = jadwal_kuliah_mahasiswa.nim "
				+ "JOIN jadwal_kuliah ON jadwal_kuliah.jadwal_kuliah_id = jadwal_kuliah_mahasiswa.jadwal_kuliah_id "
				+ "JOIN matakuliah ON matakuliah.matakuliah_id = jadwal_kuliah_mahasiswa.matakuliah_id "
				+ "WHERE jadwal_kuliah_mahasiswa.nim = " + nim.toString() + " "
				+ "ORDER BY jadwal_kuliah.jadwal_kuliah_id";
				
		try (PreparedStatement st = con.prepareQuery(query, null, null);
				ResultSet rs = st.executeQuery()) {
			while(rs.next()) {
				JadwalKuliahMahasiswa jkm = new JadwalKuliahMahasiswa(nim, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				jkmList.add(jkm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			con.closeConnection();
		}
		return jkmList;
	}
	
	public static void search(String searchBy) {
		JDBCPostgreSQLConnect conDB = new JDBCPostgreSQLConnect();
		List<Mahasiswa> listMahasiswa = getMahasiswa(searchBy, conDB);
			
		if(listMahasiswa == null) {
			return;
		}
		if(listMahasiswa.isEmpty()) {
			System.out.println("Mahasiswa not found...");
			return;
		}
		
		for (Mahasiswa mhs : listMahasiswa) {
			List<JadwalKuliahMahasiswa> jkmList = getJadwalKuliahMahasiswa(mhs.getNim(), conDB);
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
