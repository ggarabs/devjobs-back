package com.ggarabetti.devjobs_crud.domain.assignment;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, UUID> {
    List<Assignment> findAll();

    Optional<List<Assignment>> findAllByJobId(UUID id);
}
