package com.albert.processdata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = "";
			while((line = br.readLine()) != null) {
//				String[] mahasiswa = line.split(";");
//				System.out.println("NIM: " + mahasiswa[0] + ", Nama: " + mahasiswa[1]);
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Move file to done
//		try {
//			Path temp = Files.move(Paths.get(filePath), Paths.get(filePath.replace("master", "master-done")));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
