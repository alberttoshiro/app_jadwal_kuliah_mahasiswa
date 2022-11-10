package com.albert;

import com.albert.database.PostgresDatabase;
import com.albert.processdata.FolderListener;

public class App {

  public static void main(String[] args) {
    PostgresDatabase postgresDatabase = new PostgresDatabase();
    FolderListener folderListener = new FolderListener(postgresDatabase);
    Thread thread = new Thread(folderListener);
    thread.start();
  }

  // public void addMahasiswa() {
  // EntityManagerFactory emf =
  // Persistence.createEntityManagerFactory("jadwal_mahasiswa_hibernate");
  // // EntityManager em = emf.createEntityManager();
  // // System.out.println();
  // // System.out.println("before mahasiswa");
  // // Mahasiswa mhs = new Mahasiswa();
  // // // mhs.setId(UUID.randomUUID());
  // // mhs.setNama("Albert Toshiro");
  // // mhs.setNim("2201779623");
  // // EntityTransaction et = em.getTransaction();
  // // try {
  // // et.begin();
  // // if (em != null) {
  // // System.out.println("not null");
  // // }
  // // if (em == null) {
  // // System.out.println("hmm");
  // // }
  // // em.persist(mhs);
  // // et.commit();
  // // } catch (Exception e) {
  // // e.printStackTrace();
  // // System.out.println("em null");
  // // } finally {
  // // em.close();
  // // emf.close();
  // // }
  // }

  // public void testAdd() {
  // StandardServiceRegistry ssr =
  // new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
  // Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
  //
  // SessionFactory factory = meta.getSessionFactoryBuilder().build();
  // Session session = factory.openSession();
  // Transaction t = session.beginTransaction();
  //
  // Mahasiswa mahasiswa = new Mahasiswa();
  // mahasiswa.setNama("Albert Toshiro Heru");
  // mahasiswa.setNim("2201779623");
  // // session.persist(mahasiswa);
  //
  // Matakuliah matakuliah = new Matakuliah();
  // matakuliah.setNamaMatakuliah("Algorithm Design and Analysis");
  // // session.persist(matakuliah);
  //
  // JadwalKuliah jadwalKuliah = new JadwalKuliah();
  // jadwalKuliah.setHari("Senin");
  // jadwalKuliah.setRuangan("R505");
  // jadwalKuliah.setWaktuMulai(AppUtil.convertStringToLocalTime("17:00"));
  // jadwalKuliah.setWaktuSelesai(AppUtil.convertStringToLocalTime("19:00"));
  // // session.persist(jadwalKuliah);
  //
  // JadwalKuliahMahasiswa jadwalKuliahMahasiswa = new JadwalKuliahMahasiswa();
  // jadwalKuliahMahasiswa.setMahasiswa(mahasiswa);
  // jadwalKuliahMahasiswa.setMatakuliah(matakuliah);
  // jadwalKuliahMahasiswa.setJadwalKuliah(jadwalKuliah);
  // // session.persist(jadwalKuliahMahasiswa);
  //
  // t.commit();
  //
  // session.close();
  // factory.close();
  // }

}
