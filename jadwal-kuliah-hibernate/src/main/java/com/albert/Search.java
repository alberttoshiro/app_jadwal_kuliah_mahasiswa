package com.albert;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.albert.dao.JadwalKuliahDAO;
import com.albert.dao.MahasiswaDAO;
import com.albert.model.JadwalKuliah;
import com.albert.model.Mahasiswa;
import org.jboss.logging.Logger;

@Path("/search")

public class Search {

  @Inject
  Logger log;

  @Inject
  MahasiswaDAO mahasiswaDAO;

  @Inject
  JadwalKuliahDAO jadwalKuliahDAO;

  @GET
  @Path("mahasiswa/getall")
  @Produces(MediaType.APPLICATION_JSON)
  public List<com.albert.dto.MahasiswaDTO> getAll() {
    String result = "Result of get all mahasiswa: \n";
    List<Mahasiswa> listMahasiswa = mahasiswaDAO.getAll();
    List<com.albert.dto.MahasiswaDTO> resultList = new ArrayList<>();

    for (Mahasiswa mahasiswa : listMahasiswa) {
      result = result.concat(mahasiswa.toString() + "\n");
      resultList.add(new com.albert.dto.MahasiswaDTO(mahasiswa.getId(), mahasiswa.getNim(),
          mahasiswa.getNama()));
    }
    log.info(result);
    return resultList;
  }



  @GET
  @Path("jkm/getall")
  @Produces(MediaType.TEXT_PLAIN)
  public String getAllJKM() {
    String result = "Result of get all jadwal kuliah mahasiswa: \n";
    List<JadwalKuliah> list = jadwalKuliahDAO.getAll();
    for (JadwalKuliah jkm : list) {
      result = result.concat(jkm.toString() + "\n");
    }
    log.info(result);
    return result;
  }

  public List<Mahasiswa> getMahasiswa(String searchBy, String value) {
    if (searchBy.equals("nama")) {
      return mahasiswaDAO.findByNama(value);
    } else if (searchBy.equals("nim")) {
      return mahasiswaDAO.findByNim(value);
    } else {
      return null;
    }
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("mahasiswa/nim/{nim}")
  public String getMahasiswaByNim(String nim) {
    String result = "Result of get mahasiswa by nim : " + nim + "\n";
    List<Mahasiswa> listMahasiswa = mahasiswaDAO.findByNim(nim);
    for (Mahasiswa mahasiswa : listMahasiswa) {
      result = result.concat(mahasiswa.toString() + "\n");
    }
    log.info(result);
    return result;
  }

  public String printSeparator(char c) {
    String separator = "";
    for (int i = 0; i < 90; i++) {
      separator = separator.concat(Character.toString(c));
    }
    separator = separator.concat("\n");
    return separator;
  }

  public String search(String searchBy, String value) {
    List<Mahasiswa> listMahasiswa = getMahasiswa(searchBy, value);

    if (listMahasiswa == null) {
      log.info("No mahasiswa found");
      return "No mahasiswa found";
    }
    if (listMahasiswa.isEmpty()) {
      log.info("No mahasiswa found");
      return "No mahasiswa found";
    }

    String result = "Search result: \n";
    for (Mahasiswa mahasiswa : listMahasiswa) {
      List<JadwalKuliah> listJadwalKuliah = jadwalKuliahDAO.findByMahasiswa(mahasiswa);
      result = result.concat(printSeparator('='));
      result = result.concat(mahasiswa.getNama() + ", NIM: " + mahasiswa.getNim() + "\n");
      result = result.concat(printSeparator('='));
      if (listJadwalKuliah == null || listJadwalKuliah.isEmpty()) {
        result = result.concat("You have no schedule for now...\n");
        result = result.concat(printSeparator('-'));
        continue;
      }
      for (JadwalKuliah jadwalKuliah : listJadwalKuliah) {
        String jadwal = String.format("%-10s | %-40s | %-10s | %-5s - %-5s |\n",
            jadwalKuliah.getRuanganWaktu().getHari().getNamaHari(),
            jadwalKuliah.getMatakuliah().getNamaMatakuliah(),
            jadwalKuliah.getRuanganWaktu().getRuangan().getNomorRuangan(),
            jadwalKuliah.getRuanganWaktu().getWaktu().getWaktuMulai(),
            jadwalKuliah.getRuanganWaktu().getWaktu().getWaktuSelesai());
        result = result.concat(jadwal);
      }
      result = result.concat(printSeparator('-'));

    }
    log.info(result);
    return result;
  }

  @GET
  @Path("nama/{nama}")
  @Produces(MediaType.TEXT_PLAIN)
  public String searchNama(@PathParam("nama") String nama) {
    return search("nama", nama);
  }

  @GET
  @Path("nim/{nim}")
  @Produces(MediaType.TEXT_PLAIN)
  public String searchNim(@PathParam("nim") String nim) {
    return search("nim", nim);
  }
}
