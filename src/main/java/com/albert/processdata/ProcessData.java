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

import com.albert.model.Matakuliah;
import com.albert.postgresql.JDBCPostgreSQLConnect;

public class ProcessData implements Runnable {
	protected String folderName;
	protected String filePath;
	protected String query;

	public ProcessData(String folderName, String filePath, String query) {
		super();
		this.folderName = folderName;
		this.filePath = filePath;
		this.query = query;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void processFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = "";
			JDBCPostgreSQLConnect sqlCon = new JDBCPostgreSQLConnect();
			while((line = br.readLine()) != null) {
				Object[] param = line.split(";");
				sqlCon.executeUpdate(query, param);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void moveFile() {
		try {
			Files.move(Paths.get(filePath), Paths.get(filePath.replace(folderName, folderName + "-done")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		processFile();
		moveFile();

	}

}
