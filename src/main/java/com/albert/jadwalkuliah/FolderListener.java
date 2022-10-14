package com.albert.jadwalkuliah;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.albert.processdata.ProcessData;

public class FolderListener implements Runnable {

	public Thread newProcessDataThread(String folderName, String filePath, String query) {
		ProcessData task = new ProcessData(folderName, filePath, query);
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
	        		boolean correctFile = true;
	        		if(fileName.startsWith("mahasiswa")) {
	        			query = "INSERT INTO mahasiswa(nim, nama) VALUES (?, ?)";
	        		} else if(fileName.startsWith("matakuliah")) {
	        			query = "INSERT INTO matakuliah(matakuliah_id, nama_matakuliah) VALUES (?, ?)";
	        		} else if(fileName.startsWith("jadwal_kuliah")) {
	        			query = "INSERT INTO jadwal_kuliah(jadwal_kuliah_id, ruangan, hari, jam) VALUES (?, ?, ?, ?)";
	        		} else {
	        			correctFile = false;
	        		}
	        		if(correctFile) {
	        			Thread t = newProcessDataThread(folderMaster, filePath, query);
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
						ProcessJadwalKuliahMahasiswa task = new ProcessJadwalKuliahMahasiswa(filePath);
	        			Thread t = new Thread(task);
	        			t.start();
	        			query = "INSERT INTO jadwal_kuliah_mahasiswa(nim, matakuliah_id, jadwal_kuliah_id) VALUES (?, ?, ?)";
	        			newProcessDataThread(folderCompose, filePath, query);
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
