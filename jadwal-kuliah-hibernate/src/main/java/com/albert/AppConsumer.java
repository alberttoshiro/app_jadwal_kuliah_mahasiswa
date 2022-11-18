// package com.albert;
//
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.util.UUID;
// import javax.inject.Inject;
// import com.albert.dao.JadwalKuliahDAO;
// import com.albert.dao.JadwalKuliahMahasiswaDAO;
// import com.albert.dao.MahasiswaDAO;
// import com.albert.dao.MatakuliahDAO;
// import com.albert.model.JadwalKuliah;
// import com.albert.model.JadwalKuliahMahasiswa;
// import com.albert.model.Mahasiswa;
// import com.albert.model.Matakuliah;
// import com.albert.util.AppUtil;
// import org.jboss.logging.Logger;
// import io.quarkus.vertx.ConsumeEvent;
//
// public abstract class AppConsumer {
//
// @Inject
// MahasiswaDAO mahasiswaDAO;
//
// @Inject
// MatakuliahDAO matakuliahDAO;
//
// @Inject
// JadwalKuliahDAO jadwalKuliahDAO;
//
// @Inject
// JadwalKuliahMahasiswaDAO jadwalKuliahMahasiswaDAO;
//
// @Inject
// Logger log;
//
// public abstract T convertToT(String[] param);
//
// @ConsumeEvent(value = "insertJadwalKuliah", blocking = true)
// public void insertJadwalKuliah(String filePath) throws IOException {
// log.info("starting insert jadwal kuliah process");
// try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
// String line = "";
// while ((line = br.readLine()) != null) {
// String[] param = line.split(";");
// JadwalKuliah jadwalKuliah = new JadwalKuliah();
// jadwalKuliah.setId(UUID.fromString(param[0]));
// jadwalKuliah.setHari(param[1]);
// jadwalKuliah.setRuangan(param[2]);
// jadwalKuliah.setWaktuMulai(AppUtil.convertStringToLocalTime(param[3]));
// jadwalKuliah.setWaktuSelesai(AppUtil.convertStringToLocalTime(param[4]));
// jadwalKuliahDAO.save(jadwalKuliah);
// }
// } catch (IOException e) {
// log.error(e);
// throw e;
// }
// moveFile(filePath, "master", "master-done");
// }
//
// @ConsumeEvent(value = "insertJadwalKuliahMahasiswa", blocking = true)
// public void insertJadwalKuliahMahasiswa(String filePath) throws IOException {
// log.info("starting insert jadwal kuliah mahasiswa process");
// try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
// String line = "";
// while ((line = br.readLine()) != null) {
// String[] param = line.split(";");
// UUID id = UUID.fromString(param[0]);
// UUID mahasiswaId = UUID.fromString(param[1]);
// UUID matakuliahId = UUID.fromString(param[2]);
// UUID jadwalKuliahId = UUID.fromString(param[3]);
// JadwalKuliahMahasiswa jadwalKuliahMahasiswa = jadwalKuliahMahasiswaDAO
// .convertToJadwalKuliahMahasiswa(mahasiswaId, matakuliahId, jadwalKuliahId);
// jadwalKuliahMahasiswa.setId(id);
// jadwalKuliahMahasiswaDAO.save(jadwalKuliahMahasiswa);
// }
// } catch (IOException e) {
// log.error(e);
// throw e;
// }
// moveFile(filePath, "compose", "compose-done");
// }
//
// @ConsumeEvent(value = "insertMahasiswa", blocking = true)
// public void insertMahasiswa(String filePath) throws IOException {
// log.info("starting insert mahasiswa process");
// try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
// String line = "";
// while ((line = br.readLine()) != null) {
// String[] param = line.split(";");
// Mahasiswa mahasiswa = new Mahasiswa();
// mahasiswa.setId(UUID.fromString(param[0]));
// mahasiswa.setNim(param[1]);
// mahasiswa.setNama(param[2]);
// mahasiswaDAO.save(mahasiswa);
// }
// } catch (IOException e) {
// log.error(e);
// throw e;
// }
// moveFile(filePath, "master", "master-done");
// }
//
// @ConsumeEvent(value = "insertMatakuliah", blocking = true)
// public void insertMatakuliah(String filePath) throws IOException {
// log.info("starting insert matakuliah process");
// try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
// String line = "";
// while ((line = br.readLine()) != null) {
// String[] param = line.split(";");
// Matakuliah matakuliah = new Matakuliah();
// matakuliah.setId(UUID.fromString(param[0]));
// matakuliah.setNamaMatakuliah(param[1]);
// matakuliahDAO.save(matakuliah);
// }
// } catch (IOException e) {
// log.error(e);
// throw e;
// }
// moveFile(filePath, "master", "master-done");
// }
//
// public void moveFile(String filePath, String replace, String replaceWith) throws IOException {
// try {
// Files.move(Paths.get(filePath), Paths.get(filePath.replace(replace, replaceWith)));
// } catch (IOException e) {
// log.error(e);
// throw e;
// }
// }
//
// public void processFile(String filePath) throws IOException {
// log.info("starting insert matakuliah process");
// try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
// String line = "";
// while ((line = br.readLine()) != null) {
// String[] param = line.split(";");
// Matakuliah matakuliah = new Matakuliah();
// matakuliah.setId(UUID.fromString(param[0]));
// matakuliah.setNamaMatakuliah(param[1]);
// matakuliahDAO.save(matakuliah);
// }
// } catch (IOException e) {
// log.error(e);
// throw e;
// }
// moveFile(filePath, "master", "master-done");
// }
// }
