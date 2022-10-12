package com.albert.jadwalkuliah;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.albert.processdata.ProcessJadwalKuliah;
import com.albert.processdata.ProcessJadwalKuliahMahasiswa;
import com.albert.processdata.ProcessMahasiswa;
import com.albert.processdata.ProcessMatakuliah;

public class App 
{
	
    public static void main( String[] args )
    {
        File masterFolder = new File("data/master");
        File[] listOfFilesMaster = masterFolder.listFiles();
        
        for(File file : listOfFilesMaster) {
        	if(file.isFile()) {
        		String fileName = file.getName();
        		String filePath = "data/master/" + fileName;
        		if(fileName.equals("mahasiswa.csv")) {
        			ProcessMahasiswa task = new ProcessMahasiswa(filePath);
        			Thread t = new Thread(task);
        			t.start();
        		} else if(fileName.equals("matakuliah.csv")) {
        			ProcessMatakuliah task = new ProcessMatakuliah(filePath);
        			Thread t = new Thread(task);
        			t.start();
        		} else {
        			ProcessJadwalKuliah task = new ProcessJadwalKuliah(filePath);
        			Thread t = new Thread(task);
        			t.start();
        		}

        	}
        }
        
        File composeFolder = new File("data/compose");
        File[] listOfFilesCompose = composeFolder.listFiles();
        
        for (File file : listOfFilesCompose) {
			if(file.isFile()) {
				String fileName = file.getName();
				String filePath = "data/compose/" + fileName;
				if(fileName.equals("jadwal_kuliah_mahasiswa.csv")) {
					ProcessJadwalKuliahMahasiswa task = new ProcessJadwalKuliahMahasiswa(filePath);
        			Thread t = new Thread(task);
        			t.start();
				}
			}
		}
    }
}