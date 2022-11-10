package com.albert;

import java.util.List;
import com.albert.dao.JadwalKuliahMahasiswaDAO;
import com.albert.dao.MahasiswaDAO;
import com.albert.database.PostgresDatabase;
import com.albert.model.JadwalKuliahMahasiswa;
import com.albert.model.Mahasiswa;

public class Search {
  private static PostgresDatabase postgresDatabase = new PostgresDatabase();
  private static MahasiswaDAO mahasiswaDAO = new MahasiswaDAO(postgresDatabase);
  private static JadwalKuliahMahasiswaDAO jadwalKuliahMahasiswaDAO =
      new JadwalKuliahMahasiswaDAO(postgresDatabase);

  public static List<Mahasiswa> getMahasiswa(String searchBy) {
    if (searchBy.startsWith("nama:")) {
      searchBy = searchBy.replace("nama:", "");
      return mahasiswaDAO.findByNama(searchBy);
    } else if (searchBy.startsWith("nim:")) {
      searchBy = searchBy.replace("nim:", "");
      return mahasiswaDAO.findByNim(searchBy);
    } else {
      System.out.println("Please use prefix nama: or nim: followed by your name or nim");
      return null;
    }
  }

  public static void main(String[] args) {
    String search = "";
    if (args.length < 1) {
      search = "nama:";
      System.out.println("Searching with default search: " + search);
    } else {
      search = args[0];
      System.out.println("Searching with search: " + search);
    }
    search = search.toLowerCase();

    search(search);
  }

  public static void printSeparator(char c) {
    for (int i = 0; i < 90; i++) {
      System.out.print(c);
    }
    System.out.println();
  }

  public static void search(String searchBy) {
    List<Mahasiswa> listMahasiswa = getMahasiswa(searchBy);

    if (listMahasiswa == null) {
      return;
    }
    if (listMahasiswa.isEmpty()) {
      System.out.println("Mahasiswa not found...");
      return;
    }

    for (Mahasiswa mahasiswa : listMahasiswa) {
      List<JadwalKuliahMahasiswa> listJadwalKuliahMahasiswa =
          jadwalKuliahMahasiswaDAO.findByMahasiswa(mahasiswa);
      printSeparator('=');
      System.out.println(mahasiswa.getNama() + ", NIM: " + mahasiswa.getNim());
      printSeparator('=');
      if (listJadwalKuliahMahasiswa == null || listJadwalKuliahMahasiswa.isEmpty()) {
        System.out.println("You have no schedule for now...");
        printSeparator('-');
        continue;
      }
      for (JadwalKuliahMahasiswa jadwalKuliahMahasiswa : listJadwalKuliahMahasiswa) {
        System.out.printf("%-10s | %-40s | %-10s | %-5s - %-5s |%n",
            jadwalKuliahMahasiswa.getJadwalKuliah().getHari(),
            jadwalKuliahMahasiswa.getMatakuliah().getNamaMatakuliah(),
            jadwalKuliahMahasiswa.getJadwalKuliah().getRuangan(),
            jadwalKuliahMahasiswa.getJadwalKuliah().getWaktuMulai(),
            jadwalKuliahMahasiswa.getJadwalKuliah().getWaktuSelesai());
      }
      printSeparator('-');
    }
  }
}
