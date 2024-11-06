package com.ggarabetti.devjobs_crud.domain.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggarabetti.devjobs_crud.domain.model.Requirement;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, UUID> {
    List<Requirement> findAll();

    Optional<List<Requirement>> findAllByJobId(UUID id);
}
