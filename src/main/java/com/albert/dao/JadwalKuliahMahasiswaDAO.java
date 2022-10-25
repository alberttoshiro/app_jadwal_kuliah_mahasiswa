package com.albert.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.albert.model.JadwalKuliahMahasiswa;
import com.albert.postgresql.JDBCPostgreSQLConnect;

public class JadwalKuliahMahasiswaDAO extends JDBCPostgreSQLConnect<JadwalKuliahMahasiswa>
    implements BaseDAO<JadwalKuliahMahasiswa> {

  private static final String SELECT_JADWAL_KULIAH_MAHASISWA =
      "SELECT jadwal_kuliah.hari, matakuliah.nama_matakuliah, jadwal_kuliah.ruangan, jadwal_kuliah.jam "
          + "FROM jadwal_kuliah_mahasiswa "
          + "JOIN mahasiswa ON mahasiswa.nim = jadwal_kuliah_mahasiswa.nim "
          + "JOIN jadwal_kuliah ON jadwal_kuliah.jadwal_kuliah_id = jadwal_kuliah_mahasiswa.jadwal_kuliah_id "
          + "JOIN matakuliah ON matakuliah.matakuliah_id = jadwal_kuliah_mahasiswa.matakuliah_id "
          + "WHERE jadwal_kuliah_mahasiswa.nim = ? " + "ORDER BY jadwal_kuliah.jadwal_kuliah_id";

  @Override
  public void deleteById(String id) {
    // TODO Auto-generated method stub

  }

  @Override
  public JadwalKuliahMahasiswa findById(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<JadwalKuliahMahasiswa> getAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<JadwalKuliahMahasiswa> getJadwalKuliahMahasiswaByNim(Integer nim) {
    List<JadwalKuliahMahasiswa> jkmList = new ArrayList<>();
    ResultSet rs = null;
    Connection con = getConnection();
    try (PreparedStatement st = con.prepareStatement(SELECT_JADWAL_KULIAH_MAHASISWA)) {
      st.setInt(1, nim);
      rs = st.executeQuery();
      while (rs.next()) {
        JadwalKuliahMahasiswa jkm = new JadwalKuliahMahasiswa(nim, rs.getString(1), rs.getString(2),
            rs.getString(3), rs.getString(4));
        jkmList.add(jkm);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return jkmList;
  }

  @Override
  public void insert(JadwalKuliahMahasiswa data) {
    // TODO Auto-generated method stub

  }

  @Override
  public void update(JadwalKuliahMahasiswa data) {
    // TODO Auto-generated method stub

  }

}
