package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Table(name = "persistent_audit_event")
public class PersistentAuditEvent implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "event_id")
  private Integer id;

  @NotNull
  @Column(nullable = false)
  private String principal;

  @Column(name = "event_date")
  private Instant auditEventDate;

  @Column(name = "event_type")
  private String auditEventType;

  @ElementCollection
  @MapKeyColumn(name = "name")
  @Column(name = "value")
  @CollectionTable(name = "persistent_audit_evt_data", joinColumns = @JoinColumn(name = "event_id"))
  private Map<String, String> data = new HashMap<>();
}
