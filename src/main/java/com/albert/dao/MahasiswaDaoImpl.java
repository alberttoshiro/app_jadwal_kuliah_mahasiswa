package com.albert.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.albert.model.Mahasiswa;
import com.albert.postgresql.JDBCPostgreSQLConnect;

public class MahasiswaDaoImpl extends JDBCPostgreSQLConnect implements MahasiswaDao{

	private static final String SELECT_BY_NAME = "SELECT * FROM mahasiswa WHERE nama ILIKE ?";
	private static final String SELECT_BY_NIM = "SELECT * FROM mahasiswa WHERE nim = ?";

	@Override
	public List<Mahasiswa> getMahasiswaByName(String name) {
		List<Mahasiswa> listMahasiswa = new ArrayList<>();
		Connection con = getConnection();
		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(SELECT_BY_NAME)) {
			st.setString(1, "%" + name + "%");
			rs = st.executeQuery();
			while(rs.next()) {
				Mahasiswa mhs = new Mahasiswa(rs.getInt(1), rs.getString(2));
				listMahasiswa.add(mhs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return listMahasiswa;
	}

	@Override
	public List<Mahasiswa> getMahasiswaByNim(Integer nim) {
		List<Mahasiswa> listMahasiswa = new ArrayList<>();
		ResultSet rs = null;
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(SELECT_BY_NIM)) {
			st.setInt(1, nim);
			rs = st.executeQuery();
			while(rs.next()) {
				Mahasiswa mhs = new Mahasiswa(rs.getInt(1), rs.getString(2));
				listMahasiswa.add(mhs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return listMahasiswa;
	}

}
