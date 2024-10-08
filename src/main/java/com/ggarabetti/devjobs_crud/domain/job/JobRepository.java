package com.ggarabetti.devjobs_crud.domain.job;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {
    List<Job> findAll();
}
