package com.bugreseach.jpaauditing.adapter.income.rest.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ChildEntityDto {

  UUID id;
  String name;
  int version;
}
