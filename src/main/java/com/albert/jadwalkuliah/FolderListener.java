package com.albert.jadwalkuliah;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.albert.dao.JadwalKuliahDAO;
import com.albert.dao.JadwalKuliahMahasiswaDAO;
import com.albert.dao.MahasiswaDAO;
import com.albert.dao.MatakuliahDAO;
import com.albert.processdata.ProcessDataJadwalKuliah;
import com.albert.processdata.ProcessDataJadwalKuliahMahasiswa;
import com.albert.processdata.ProcessDataMahasiswa;
import com.albert.processdata.ProcessDataMatakuliah;

public class FolderListener implements Runnable {

  private static final String URL = "jdbc:postgresql://localhost/jadwal_mahasiswa";
  private static final String USERNAME = "postgres";
  private static final String PASSWORD = "postgres";

  private MahasiswaDAO mahasiswaDAO = new MahasiswaDAO(URL, USERNAME, PASSWORD);
  private MatakuliahDAO matakuliahDAO = new MatakuliahDAO(URL, USERNAME, PASSWORD);
  private JadwalKuliahDAO jadwalKuliahDAO = new JadwalKuliahDAO(URL, USERNAME, PASSWORD);
  private JadwalKuliahMahasiswaDAO jadwalKuliahMahasiswaDAO =
      new JadwalKuliahMahasiswaDAO(URL, USERNAME, PASSWORD);

  public void newProcessDataThread(Runnable runnableTask, List<Thread> threads) {
    Thread t = new Thread(runnableTask);
    t.start();
    if (threads != null) {
      threads.add(t);
    }
  }

  @Override
  public void run() {
    String root = "data/";
    String folderMaster = "master";
    File masterFolder = new File(root + folderMaster);

    while (true) {
      List<Thread> threads = new ArrayList<>();
      File[] listOfFilesMaster = masterFolder.listFiles();
      for (File file : listOfFilesMaster) {
        if (file.isFile()) {
          String fileName = file.getName();
          String filePath = "data/master/" + fileName;

          if (fileName.startsWith("mahasiswa")) {
            ProcessDataMahasiswa processDataMahasiswa =
                new ProcessDataMahasiswa(folderMaster, filePath, mahasiswaDAO);
            newProcessDataThread(processDataMahasiswa, threads);
          } else if (fileName.startsWith("matakuliah")) {
            ProcessDataMatakuliah processDataMatakuliah =
                new ProcessDataMatakuliah(folderMaster, filePath, matakuliahDAO);
            newProcessDataThread(processDataMatakuliah, threads);
          } else if (fileName.startsWith("jadwal_kuliah")) {
            ProcessDataJadwalKuliah processDataJadwalKuliah =
                new ProcessDataJadwalKuliah(folderMaster, filePath, jadwalKuliahDAO);
            newProcessDataThread(processDataJadwalKuliah, threads);
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
        if (file.isFile()) {
          String fileName = file.getName();
          String filePath = "data/compose/" + fileName;

          if (fileName.startsWith("jadwal_kuliah_mahasiswa")) {
            ProcessDataJadwalKuliahMahasiswa processDataJadwalKuliahMahasiswa =
                new ProcessDataJadwalKuliahMahasiswa(folderCompose, filePath,
                    jadwalKuliahMahasiswaDAO);
            newProcessDataThread(processDataJadwalKuliahMahasiswa, null);
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
