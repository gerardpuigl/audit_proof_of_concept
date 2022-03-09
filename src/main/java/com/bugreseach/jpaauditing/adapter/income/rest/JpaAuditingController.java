package com.bugreseach.jpaauditing.adapter.income.rest;

import com.bugreseach.jpaauditing.adapter.income.rest.dto.ParentEntityDto;
import com.bugreseach.jpaauditing.adapter.income.rest.mapper.ControllerDtoMapper;
import com.bugreseach.jpaauditing.application.interactor.JpaAutidingInteractor;
import com.bugreseach.jpaauditing.domain.ParentEntity;
import java.util.UUID;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpaAuditingController {

  @Autowired
  JpaAutidingInteractor service;

  ControllerDtoMapper mapper = Mappers.getMapper(ControllerDtoMapper.class);

  @PostMapping("/create")
  public ParentEntity createA(@RequestBody ParentEntityDto parentEntity){
    return service.create( mapper.toDom(parentEntity));
  }

  @PutMapping("/update/{id}")
  public ParentEntity updateA(@PathVariable UUID id, @RequestBody ParentEntityDto parentEntityDto){
    ParentEntity parentEntity = mapper.toDom(parentEntityDto);
    parentEntity.setId(id);
    return service.update(parentEntity);
  }

  @GetMapping("/get/{id}")
  public ParentEntity getA(@PathVariable UUID id){
    return service.get(id);
  }
}
