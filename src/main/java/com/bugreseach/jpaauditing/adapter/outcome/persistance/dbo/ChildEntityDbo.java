package com.bugreseach.jpaauditing.adapter.outcome.persistance.dbo;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OptimisticLock;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "child_entity")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode

public class ChildEntityDbo {

  @Id
  UUID id = UUID.randomUUID();

  String name;

  @Column(name = "created_at", nullable = false, updatable = false)
  @OptimisticLock(excluded = true)
  @CreationTimestamp
  LocalDateTime createdAt;

  @OptimisticLock(excluded = true)
  @UpdateTimestamp
  LocalDateTime lastModifiedDate;

  @Version
  int version;

  @Column(name = "parent_entity_id")
  private UUID parentEntityId;

  /** RELATIONAL OBJECTS */
  @ManyToOne(optional = false)
  @JoinColumn(name = "parent_entity_id", nullable = false, insertable = false, updatable = false)
  private ParentEntityDbo parentEntity;

}
