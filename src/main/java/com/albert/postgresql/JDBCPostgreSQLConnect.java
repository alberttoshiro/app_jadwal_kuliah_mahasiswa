package com.albert.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.albert.model.JadwalKuliahMahasiswa;
import com.albert.model.Mahasiswa;

public class JDBCPostgreSQLConnect {
	
	private static final String URL = "jdbc:postgresql://localhost/jadwal_mahasiswa";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres";
	
	public Connection con = null;
	
	public Connection getConnection() {
		if(con == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public PreparedStatement prepareQuery(String query, Object[] param) {
		try(Connection con = getConnection(); PreparedStatement st = con.prepareStatement(query)) {
			for(int i = 0 ; i < param.length ; i++) {
				st.setObject(i + 1, param[i]);
			}
			return st;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void executeUpdate(String query, Object[] param) {		
		try (PreparedStatement st = prepareQuery(query, param)) {
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Mahasiswa> getMahasiswa(String searchBy) {
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
		
		try (PreparedStatement st = prepareQuery(searchSQL, null);
				ResultSet rs = st.executeQuery()) {
			while(rs.next()) {
				Mahasiswa mhs = new Mahasiswa(rs.getInt(1), rs.getString(2));
				listMahasiswa.add(mhs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return listMahasiswa;
	}
	
	public List<JadwalKuliahMahasiswa> getJadwalKuliahMahasiswa(Integer nim) {			
		List<JadwalKuliahMahasiswa> jkmList = new ArrayList<>();
		
		String query = "SELECT jadwal_kuliah.hari, matakuliah.nama_matakuliah, jadwal_kuliah.ruangan, jadwal_kuliah.jam "
				+ "FROM jadwal_kuliah_mahasiswa "
				+ "JOIN mahasiswa ON mahasiswa.nim = jadwal_kuliah_mahasiswa.nim "
				+ "JOIN jadwal_kuliah ON jadwal_kuliah.jadwal_kuliah_id = jadwal_kuliah_mahasiswa.jadwal_kuliah_id "
				+ "JOIN matakuliah ON matakuliah.matakuliah_id = jadwal_kuliah_mahasiswa.matakuliah_id "
				+ "WHERE jadwal_kuliah_mahasiswa.nim = " + nim.toString() + " "
				+ "ORDER BY jadwal_kuliah.jadwal_kuliah_id";
				
		try (PreparedStatement st = prepareQuery(query, null);
				ResultSet rs = st.executeQuery()) {
			while(rs.next()) {
				JadwalKuliahMahasiswa jkm = new JadwalKuliahMahasiswa(nim, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				jkmList.add(jkm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return jkmList;
	}
}
