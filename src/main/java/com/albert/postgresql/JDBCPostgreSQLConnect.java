package com.albert.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public void closeConnection() {
		if(con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public PreparedStatement prepareQuery(String query, Object[] param, String[] dataType) {
		try {
			Connection con = getConnection(); 
			PreparedStatement st = con.prepareStatement(query);
			if(param != null && dataType != null) {
				for(int i = 0 ; i < param.length ; i++) {
					if(dataType[i].equals("int")) {
						st.setInt(i + 1, Integer.valueOf(param[i].toString()));
					} else {
						st.setString(i + 1, (String) param[i]);
					}
				}
			}	
			return st;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void executeUpdate(String query, Object[] param, String[] dataType) {		
		try (PreparedStatement st = prepareQuery(query, param, dataType)) {
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}
	
	
}
