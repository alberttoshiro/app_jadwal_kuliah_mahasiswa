// package com.albert.processdata;
//
// import java.util.UUID;
// import com.albert.dao.MatakuliahDAO;
// import com.albert.model.Matakuliah;
//
// public class ProcessDataMatakuliah extends ProcessData<MatakuliahDAO, Matakuliah> {
//
// public ProcessDataMatakuliah(String folderName, String filePath, MatakuliahDAO DAO) {
// super(folderName, filePath, DAO);
// }
//
// @Override
// public Matakuliah convertToU(String[] param) {
// Matakuliah matakuliah = new Matakuliah();
// matakuliah.setId(UUID.fromString(param[0]));
// matakuliah.setNamaMatakuliah(param[1]);
// return matakuliah;
// }
//
//
// }
