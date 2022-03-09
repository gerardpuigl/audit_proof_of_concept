package com.bugreseach.jpaauditing.application.interactor;

import com.bugreseach.jpaauditing.adapter.outcome.persistance.RepositoryAdapter;
import com.bugreseach.jpaauditing.domain.ParentEntity;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaAutidingInteractor {

  @Autowired
  RepositoryAdapter repository;

  public ParentEntity create(ParentEntity parentEntity) {

    return repository.save(parentEntity);
  }

  public ParentEntity update(ParentEntity parentEntity) {

    //save the updated entity
    repository.update(parentEntity);

    //get and return new one to get the @CreatedDate
    return repository.findById(parentEntity.getId()).get();
  }

  public ParentEntity get(UUID id) {
    return repository.findById(id).get();
  }
}
