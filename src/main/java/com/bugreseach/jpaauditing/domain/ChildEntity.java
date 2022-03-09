package com.bugreseach.jpaauditing.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;


@Getter
@Setter
@NoArgsConstructor
public class ChildEntity {

  UUID id = UUID.randomUUID();
  String name;
  LocalDateTime createdAt;
  LocalDateTime lastModifiedDate;
  int version;
}
