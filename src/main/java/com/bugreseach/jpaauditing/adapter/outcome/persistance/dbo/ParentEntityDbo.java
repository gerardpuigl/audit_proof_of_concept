package com.bugreseach.jpaauditing.adapter.outcome.persistance.dbo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "parent_entity")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ParentEntityDbo {

  @Id
  UUID id = UUID.randomUUID();

  String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentEntity", cascade = CascadeType.ALL, orphanRemoval=true)
  List<ChildEntityDbo> childEntityList;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @UpdateTimestamp
  LocalDateTime lastModifiedDate;

  @Version
  int version = 0;
}
