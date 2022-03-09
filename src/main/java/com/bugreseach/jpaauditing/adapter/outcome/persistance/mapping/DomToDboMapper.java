package com.bugreseach.jpaauditing.adapter.outcome.persistance.mapping;

import com.bugreseach.jpaauditing.adapter.outcome.persistance.dbo.ChildEntityDbo;
import com.bugreseach.jpaauditing.adapter.outcome.persistance.dbo.ParentEntityDbo;
import com.bugreseach.jpaauditing.domain.ChildEntity;
import com.bugreseach.jpaauditing.domain.ParentEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DomToDboMapper {

  ParentEntityDbo parentEntityToDbo(ParentEntity parentEntity);

  ChildEntityDbo childEntityToDbo(ChildEntity childEntityList);

  List<ChildEntityDbo> childEntityListToDbo(List<ChildEntity> childEntityList);
}
