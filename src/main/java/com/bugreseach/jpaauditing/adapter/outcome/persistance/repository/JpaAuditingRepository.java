package com.bugreseach.jpaauditing.adapter.outcome.persistance.repository;

import com.bugreseach.jpaauditing.adapter.outcome.persistance.dbo.ParentEntityDbo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAuditingRepository extends JpaRepository<ParentEntityDbo, UUID>,
    JpaSpecificationExecutor<ParentEntityDbo> {

}
