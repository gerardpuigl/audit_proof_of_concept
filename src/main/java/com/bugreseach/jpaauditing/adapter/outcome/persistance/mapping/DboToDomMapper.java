package com.bugreseach.jpaauditing.adapter.outcome.persistance.mapping;

import com.bugreseach.jpaauditing.adapter.outcome.persistance.dbo.ChildEntityDbo;
import com.bugreseach.jpaauditing.adapter.outcome.persistance.dbo.ParentEntityDbo;
import com.bugreseach.jpaauditing.domain.ChildEntity;
import com.bugreseach.jpaauditing.domain.ParentEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface DboToDomMapper {

  ParentEntity parentEntityToDom(ParentEntityDbo parentEntity);

  ChildEntity childEntityToDom(ChildEntityDbo childEntityList);
  List<ChildEntity> childEntityListToDom(List<ChildEntityDbo> childEntityDboList);
}
