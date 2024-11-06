package com.ggarabetti.devjobs_crud.domain.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggarabetti.devjobs_crud.domain.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, UUID> {
    List<Assignment> findAll();

    Optional<List<Assignment>> findAllByJobId(UUID id);
}
