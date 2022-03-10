package com.bugreseach.jpaauditing.adapter.outcome.persistance;

import com.bugreseach.jpaauditing.adapter.outcome.persistance.dbo.ChildEntityDbo;
import com.bugreseach.jpaauditing.adapter.outcome.persistance.dbo.ParentEntityDbo;
import com.bugreseach.jpaauditing.adapter.outcome.persistance.mapping.DboToDomMapper;
import com.bugreseach.jpaauditing.adapter.outcome.persistance.mapping.DomToDboMapper;
import com.bugreseach.jpaauditing.adapter.outcome.persistance.repository.JpaAuditingRepository;
import com.bugreseach.jpaauditing.domain.ParentEntity;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.StaleObjectStateException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor

public class RepositoryAdapter {

  @Autowired
  JpaAuditingRepository repository;

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

    //update dates
    updateParentEntity(newParentEntity, oldParentEntity);

    //set foregint keys
    newParentEntity.getChildEntityList()
        .forEach(childEntity -> childEntity.setParentEntityId(newParentEntity.getId()));

    try {
      repository.save(newParentEntity);
    } catch (ObjectOptimisticLockingFailureException e){
      System.out.println(e.getMessage() +
          " \n " + " The entity with id: " + e.getIdentifier() + " detected by version change number.");
    }

  }

  private void updateParentEntity(ParentEntityDbo newParentEntity, ParentEntityDbo oldParentEntity) {

    //update parent dates
    newParentEntity.setCreatedAt(oldParentEntity.getCreatedAt());
    newParentEntity.setLastModifiedDate(oldParentEntity.getLastModifiedDate());

    //update child dates if match
    newParentEntity.getChildEntityList()
        .forEach(
            newChild -> {
              oldParentEntity.getChildEntityList().stream()
                  .filter(oldchild -> oldchild.getId().equals(newChild.getId()))
                  .forEach(oldChild -> {
                    newChild.setCreatedAt(oldChild.getCreatedAt());
                    newChild.setLastModifiedDate(oldChild.getLastModifiedDate());
                  });
            }
        );
  }

  @Transactional
  public Optional<ParentEntity> findById(UUID id) {

    return repository.findById(id).map(toDomMapper::parentEntityToDom);
  }
}
