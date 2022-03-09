package com.bugreseach.jpaauditing.adapter.income.rest.mapper;

import com.bugreseach.jpaauditing.adapter.income.rest.dto.ChildEntityDto;
import com.bugreseach.jpaauditing.adapter.income.rest.dto.ParentEntityDto;
import com.bugreseach.jpaauditing.domain.ChildEntity;
import com.bugreseach.jpaauditing.domain.ParentEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ControllerDtoMapper {

  ParentEntity toDom(ParentEntityDto parentEntity);

  ChildEntity toDom(ChildEntityDto childEntityList);

  List<ChildEntity> toDom(List<ChildEntityDto> childEntityDtoList);
  
}
