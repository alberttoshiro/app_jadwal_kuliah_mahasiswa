package com.albert.model;

import java.util.UUID;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  public BaseEntity() {
    super();
  }

  public BaseEntity(UUID id) {
    super();
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("BaseTable [id=").append(id).append("]");
    return builder.toString();
  }
}
