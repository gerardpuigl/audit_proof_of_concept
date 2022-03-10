package com.bugreseach.jpaauditing.adapter.outcome.persistance.dbo;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "child_entity")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class ChildEntityDbo {

  @Id
  UUID id = UUID.randomUUID();

  String name;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreatedDate
  @OptimisticLock(excluded = true) // avoid version update version change
  LocalDateTime createdAt;

  @LastModifiedDate
  @OptimisticLock(excluded = true) // avoid version update version change
  LocalDateTime lastModifiedDate;

  @Version
  int version;

  @Column(name = "parent_entity_id")
  private UUID parentEntityId;

  /** RELATIONAL OBJECTS */
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  @JoinColumn(name = "parent_entity_id", nullable = false, insertable = false, updatable = false)
  private ParentEntityDbo parentEntity;

}
