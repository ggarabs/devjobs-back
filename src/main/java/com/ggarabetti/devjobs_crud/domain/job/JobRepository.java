package com.ggarabetti.devjobs_crud.domain.job;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {
    List<Job> findAll();

    Optional<List<Job>> findAllById(UUID id);
}
