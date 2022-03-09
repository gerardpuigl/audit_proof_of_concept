package com.bugreseach.jpaauditing.adapter.outcome.persistance.mapping;

import com.bugreseach.jpaauditing.adapter.outcome.persistance.dbo.ChildEntityDbo;
import com.bugreseach.jpaauditing.adapter.outcome.persistance.dbo.ParentEntityDbo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UpdateMapper {

  ParentEntityDbo updateParentEntity(
      ParentEntityDbo newEntity, @MappingTarget ParentEntityDbo originalEntity);

  //mapper to update the entity but keep the @CreatedDate & @UpdateDate
  default void updateChildEntityList(List<ChildEntityDbo> newChildEntityDboList,
      @MappingTarget List<ChildEntityDbo> oldChildEntityDboList) {
    List<ChildEntityDbo> updatedArrayList = new ArrayList<>();
    newChildEntityDboList
        .forEach(
            newChild ->
            {
              Optional<ChildEntityDbo> oldAddress = oldChildEntityDboList.stream()
                  .filter(child -> child.getId().equals(newChild.getId()))
                  .findAny();
              if (oldAddress.isPresent()) {
                updatedArrayList.add(updateChildEntity(newChild,oldAddress.get()));
              } else {
                updatedArrayList.add(newChild);
              }
            }
        );
    oldChildEntityDboList.clear();
    oldChildEntityDboList.addAll(updatedArrayList);
  }

  ChildEntityDbo updateChildEntity(
      ChildEntityDbo newEntity, @MappingTarget ChildEntityDbo originalEntity);
}
