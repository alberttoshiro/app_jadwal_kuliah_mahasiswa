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
import com.albert.model.JadwalKuliahMahasiswa;
import com.albert.postgresql.JDBCPostgreSQLConnect;

public class ProcessJadwalKuliahMahasiswa implements Runnable {

	private String filePath;

	public ProcessJadwalKuliahMahasiswa(String filePath) {
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
			String sql = "INSERT INTO jadwal_kuliah_mahasiswa(nim, matakuliah_id, jadwal_kuliah_id) VALUES (?, ?, ?)";
			st = con.prepareStatement(sql);
			
			String line = "";
			while((line = br.readLine()) != null) {
				String[] jadwalKuliahMahasiswa = line.split(";");
//						System.out.println(line);
				JadwalKuliahMahasiswa jKMhs = new JadwalKuliahMahasiswa(Integer.parseInt(jadwalKuliahMahasiswa[0]), Integer.parseInt(jadwalKuliahMahasiswa[1]), Integer.parseInt(jadwalKuliahMahasiswa[2]));
				st.setInt(1, jKMhs.getNim());
				st.setInt(2, jKMhs.getMatakuliahId());
				st.setInt(3, jKMhs.getJadwalKuliahId());
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
			Path temp = Files.move(Paths.get(filePath), Paths.get(filePath.replace("compose", "compose-done")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
