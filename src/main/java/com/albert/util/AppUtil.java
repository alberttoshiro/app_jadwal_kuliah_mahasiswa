package com.albert.util;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppUtil {

  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

  public static String convertTime(LocalTime value) {
    return TIME_FORMATTER.format(value);
  }

  public static Time convertTime(String value) {
    return Time.valueOf(LocalTime.parse(value, TIME_FORMATTER));
  }
}
