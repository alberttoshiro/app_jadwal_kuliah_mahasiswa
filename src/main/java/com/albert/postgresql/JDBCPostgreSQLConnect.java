package com.albert.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCPostgreSQLConnect {
	
	private static final String URL = "jdbc:postgresql://localhost/jadwal_mahasiswa";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres";
	
	public static Connection con = null;
	
	public static Connection getConnection() {
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
}
