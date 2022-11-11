package com.albert.util;

import java.util.HashMap;
import java.util.Map;

public class HariUtil {
  private static Map<String, Integer> hariMapping;

  private static void buildMap() {
    hariMapping = new HashMap<>();
    hariMapping.put("Senin", 1);
    hariMapping.put("Selasa", 2);
    hariMapping.put("Rabu", 3);
    hariMapping.put("Kamis", 4);
    hariMapping.put("Jumat", 5);
    hariMapping.put("Sabtu", 6);
    hariMapping.put("Minggu", 7);
  }

  public static Integer getOrder(String hari) {
    if (hariMapping == null) {
      buildMap();
    }
    if (hariMapping.containsKey(hari)) {
      return hariMapping.get(hari);
    } else {
      return 0;
    }
  }
}
