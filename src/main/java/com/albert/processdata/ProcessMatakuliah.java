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

import com.albert.model.Matakuliah;
import com.albert.postgresql.JDBCPostgreSQLConnect;

public class ProcessMatakuliah implements Runnable {
	private String filePath;

	public ProcessMatakuliah(String filePath) {
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
			String sql = "INSERT INTO matakuliah(matakuliah_id, nama_matakuliah) VALUES (?, ?)";
			st = con.prepareStatement(sql);
			
			String line = "";
			while((line = br.readLine()) != null) {
				String[] matakuliah = line.split(";");
//				System.out.println(line);
				Matakuliah matkul = new Matakuliah(Integer.parseInt(matakuliah[0]), matakuliah[1]);
				st.setInt(1, matkul.getMatakuliahId());
				st.setString(2, matkul.getNamaMatakuliah());
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
