package com.albert.jadwalkuliah;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.albert.processdata.ProcessData;

public class FolderListener implements Runnable {

	public Thread newProcessDataThread(String folderName, String filePath, String query, String[] dataType) {
		ProcessData task = new ProcessData(folderName, filePath, query, dataType);
		Thread t = new Thread(task);
		t.start();
		return t;
	}
	
	@Override
	public void run() {
		String root = "data/";
		String folderMaster = "master";
		File masterFolder = new File(root + folderMaster);      
        
		while(true) {	
			List<Thread> threads = new ArrayList<>();
			File[] listOfFilesMaster = masterFolder.listFiles();
	        for(File file : listOfFilesMaster) {
	        	if(file.isFile()) {
	        		String fileName = file.getName();
	        		String filePath = "data/master/" + fileName;
	        		String query = "";
	        		String[] dataType;
	        		boolean correctFile = true;
	        		if(fileName.startsWith("mahasiswa")) {
	        			query = "INSERT INTO mahasiswa(nim, nama) VALUES (?, ?)";
	        			dataType = new String[2];
	        			dataType[0] = "int";
	        			dataType[1] = "string";
	        		} else if(fileName.startsWith("matakuliah")) {
	        			query = "INSERT INTO matakuliah(matakuliah_id, nama_matakuliah) VALUES (?, ?)";
	        			dataType = new String[2];
	        			dataType[0] = "int";
	        			dataType[1] = "string";
	        		} else if(fileName.startsWith("jadwal_kuliah")) {
	        			query = "INSERT INTO jadwal_kuliah(jadwal_kuliah_id, ruangan, hari, jam) VALUES (?, ?, ?, ?)";
	        			dataType = new String[4];
	        			dataType[0] = "int";
	        			dataType[1] = "string";
	        			dataType[2] = "string";
	        			dataType[3] = "string";
	        		} else {
	        			correctFile = false;
	        			dataType = null;
	        		}
	        		if(correctFile) {
	        			Thread t = newProcessDataThread(folderMaster, filePath, query, dataType);
	        			threads.add(t);
	        		}
	        	}
	        }
	        
	        for (Thread thread : threads) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	        
	        String folderCompose = "compose";
	        File composeFolder = new File(root + folderCompose);
	        File[] listOfFilesCompose = composeFolder.listFiles();
	        
	        for (File file : listOfFilesCompose) {
				if(file.isFile()) {
					String fileName = file.getName();
					String filePath = "data/compose/" + fileName;
					String query = "";
					if(fileName.startsWith("jadwal_kuliah_mahasiswa")) {
	        			query = "INSERT INTO jadwal_kuliah_mahasiswa(nim, matakuliah_id, jadwal_kuliah_id) VALUES (?, ?, ?)";
	        			String[] dataType = {"int", "int", "int"};
	        			newProcessDataThread(folderCompose, filePath, query, dataType);
					}
				}
			}
	        
	        try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
}
