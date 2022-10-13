package com.albert.processdata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.albert.model.Mahasiswa;
import com.albert.postgresql.JDBCPostgreSQLConnect;

public class ProcessMahasiswa implements Runnable {
	
	private String filePath;

	public ProcessMahasiswa(String filePath) {
		super();
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run() {
		// Read data and add to database
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			Connection con;
			PreparedStatement st;
			con = JDBCPostgreSQLConnect.getConnection();
			String sql = "INSERT INTO mahasiswa(nim, nama) VALUES (?, ?)";
			st = con.prepareStatement(sql);
			
			String line = "";
			while((line = br.readLine()) != null) {
				String[] mahasiswa = line.split(";");
//				System.out.println(line);
				Mahasiswa mhs = new Mahasiswa(Integer.parseInt(mahasiswa[0]), mahasiswa[1]);
				st.setInt(1, mhs.getNim());
				st.setString(2, mhs.getNama());
				st.executeUpdate();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Move file to done
		try {
			Path temp = Files.move(Paths.get(filePath), Paths.get(filePath.replace("master", "master-done")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
