package com.ggarabetti.devjobs_crud.domain.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggarabetti.devjobs_crud.domain.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {
    List<Job> findAll();

    Optional<List<Job>> findAllById(UUID id);
}
