package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "authority", schema = "testdb")
public class Authority implements Serializable {
  @Id
  @Column(name = "name")
  private String roleName;
}
