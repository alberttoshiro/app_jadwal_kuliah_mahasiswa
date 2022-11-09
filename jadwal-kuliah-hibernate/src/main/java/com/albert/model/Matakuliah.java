package com.albert.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class Matakuliah extends BaseEntity {

  @Column(name = "nama_matakuliah")
  private String namaMatakuliah;

  public Matakuliah() {
    super();
  }

  public Matakuliah(UUID id, String namaMatakuliah) {
    super(id);
    this.namaMatakuliah = namaMatakuliah;
  }
}
