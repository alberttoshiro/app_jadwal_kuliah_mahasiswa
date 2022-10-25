package com.albert.model;

import java.io.Serializable;
import java.time.LocalTime;

public abstract class BaseScheduleTable extends BaseTable implements Serializable {

  private static final long serialVersionUID = -6390880593638813799L;

  private LocalTime startTime;
  private LocalTime endTime;

  public BaseScheduleTable() {
    super();
  }

  public BaseScheduleTable(String id, LocalTime startTime, LocalTime endTime) {
    super(id);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("BaseScheduleTable [startTime=").append(startTime).append(", endTime=")
        .append(endTime).append(", toString()=").append(super.toString()).append("]");
    return builder.toString();
  }

}
