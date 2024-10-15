package com.ggarabetti.devjobs_crud.domain.requirements;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, UUID> {
    List<Requirement> findAll();

    Optional<List<Requirement>> findAllByJobId(UUID id);
}
