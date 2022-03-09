package com.bugreseach.jpaauditing.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ParentEntity {

  UUID id = UUID.randomUUID();
  String name;
  List<ChildEntity> childEntityList;
  LocalDateTime createdAt;
  LocalDateTime lastModifiedDate;
  int version;
}
