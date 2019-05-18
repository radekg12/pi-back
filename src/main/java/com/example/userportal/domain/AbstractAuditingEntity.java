package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@Audited
@Data
@EntityListeners(AuditingEntityListener.class)
abstract class AbstractAuditingEntity implements Serializable {

  @CreatedBy
  @Column(name = "created_by", nullable = false, length = 50, updatable = false)
  @JsonIgnore
  private String createdBy;

  @CreatedDate
  @Temporal(TIMESTAMP)
  @Column(name = "created_date", updatable = false)
  @JsonIgnore
  private Date createdDate;

  @LastModifiedBy
  @Column(name = "last_modified_by", length = 50)
  @JsonIgnore
  private String lastModifiedBy;

  @LastModifiedDate
  @Temporal(TIMESTAMP)
  @Column(name = "last_modified_date")
  @JsonIgnore
  private Date lastModifiedDate;
}
