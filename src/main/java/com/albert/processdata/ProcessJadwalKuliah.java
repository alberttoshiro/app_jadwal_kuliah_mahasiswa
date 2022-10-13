package com.albert.processdata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.albert.model.JadwalKuliah;
import com.albert.postgresql.JDBCPostgreSQLConnect;

public class ProcessJadwalKuliah implements Runnable {

	private String filePath;

	public ProcessJadwalKuliah(String filePath) {
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
			String sql = "INSERT INTO jadwal_kuliah(jadwal_kuliah_id, ruangan, hari, jam) VALUES (?, ?, ?, ?)";
			st = con.prepareStatement(sql);
			
			String line = "";
			while((line = br.readLine()) != null) {
				String[] jadwalKuliah = line.split(";");
//				System.out.println(line);
				JadwalKuliah jKuliah = new JadwalKuliah(Integer.parseInt(jadwalKuliah[0]), jadwalKuliah[1], jadwalKuliah[2], jadwalKuliah[3]);
				st.setInt(1, jKuliah.getJadwalKuliahId());
				st.setString(2, jKuliah.getRuangan());
				st.setString(3, jKuliah.getHari());
				st.setString(4, jKuliah.getJam());
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
