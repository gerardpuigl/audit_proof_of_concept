package com.bugreseach.jpaauditing.adapter.outcome.persistance;

import com.bugreseach.jpaauditing.adapter.outcome.persistance.dbo.ParentEntityDbo;
import com.bugreseach.jpaauditing.adapter.outcome.persistance.mapping.DboToDomMapper;
import com.bugreseach.jpaauditing.adapter.outcome.persistance.mapping.DomToDboMapper;
import com.bugreseach.jpaauditing.adapter.outcome.persistance.mapping.UpdateMapper;
import com.bugreseach.jpaauditing.adapter.outcome.persistance.repository.JpaAuditingRepository;
import com.bugreseach.jpaauditing.domain.ParentEntity;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor

public class RepositoryAdapter {

  @Autowired
  JpaAuditingRepository repository;

  UpdateMapper updateMapper = Mappers.getMapper(UpdateMapper.class);
  DomToDboMapper toDboMapper = Mappers.getMapper(DomToDboMapper.class);
  DboToDomMapper toDomMapper = Mappers.getMapper(DboToDomMapper.class);

  @Transactional
  public ParentEntity save(ParentEntity parentEntity) {

    //map dom to dbo
    ParentEntityDbo parentEntityDbo = toDboMapper.parentEntityToDbo(parentEntity);

    //set foregint keys
    parentEntityDbo.getChildEntityList()
        .forEach(childEntity -> childEntity.setParentEntityId(parentEntityDbo.getId()));

    return toDomMapper.parentEntityToDom(repository.save(parentEntityDbo));

  }
  @Transactional
  public void update(ParentEntity parentEntity) {

    //map dom to dbo
    ParentEntityDbo newParentEntity = toDboMapper.parentEntityToDbo(parentEntity);

    //get old Entity
    ParentEntityDbo oldParentEntity = repository.findById(newParentEntity.getId())
        .orElseThrow(()->new RuntimeException("Entity with id " + newParentEntity.getId().toString() + "not found."));

//    //update old entity
//    ParentEntityDbo updatedParentEntity = updateMapper.updateParentEntity(newParentEntity,
//        oldParentEntity);

    //set foregint keys
    newParentEntity.getChildEntityList()
        .forEach(childEntity -> childEntity.setParentEntityId(newParentEntity.getId()));

    repository.save(newParentEntity);
  }

  @Transactional
  public Optional<ParentEntity> findById(UUID id) {

    return repository.findById(id).map(toDomMapper::parentEntityToDom);
  }
}
