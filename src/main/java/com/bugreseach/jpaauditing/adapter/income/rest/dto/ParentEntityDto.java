package com.bugreseach.jpaauditing.adapter.income.rest.dto;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ParentEntityDto {

  UUID id;
  String name;
  List<ChildEntityDto> childEntityList;
  int version;
}
