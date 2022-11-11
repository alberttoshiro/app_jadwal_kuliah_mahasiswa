package com.albert;

import com.albert.processdata.FolderListener;

public class App {

  public static void main(String[] args) {
    FolderListener folderListener = new FolderListener();
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

}
