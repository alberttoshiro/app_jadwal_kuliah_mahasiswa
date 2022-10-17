package com.albert.processdata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.albert.postgresql.JDBCPostgreSQLConnect;

public class ProcessData implements Runnable {
	private String folderName;
	private String filePath;
	private String query;
	private String[] dataType;

	public ProcessData(String folderName, String filePath, String query, String[] dataType) {
		super();
		this.folderName = folderName;
		this.filePath = filePath;
		this.query = query;
		this.dataType = dataType;
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

	public String[] getDataType() {
		return dataType;
	}

	public void setDataType(String[] dataType) {
		this.dataType = dataType;
	}

	public boolean processFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = "";
			JDBCPostgreSQLConnect sqlCon = new JDBCPostgreSQLConnect();
			while((line = br.readLine()) != null) {
				Object[] param = line.split(";");
//				for(int i = 0 ; i < param.length ; i++) {
//					System.out.print(param[i].toString() + ", " );
//				}
//				System.out.println();
				sqlCon.executeUpdate(query, param, dataType);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
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
		if(processFile()) {
			moveFile();
		}
	}

}
