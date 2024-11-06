package com.ggarabetti.devjobs_crud.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.ggarabetti.devjobs_crud.application.dto.RequirementRequestDTO;
import com.ggarabetti.devjobs_crud.domain.model.Job;
import com.ggarabetti.devjobs_crud.domain.model.Requirement;
import com.ggarabetti.devjobs_crud.domain.repository.JobRepository;
import com.ggarabetti.devjobs_crud.domain.repository.RequirementRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RequirementService {

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private JobRepository jobRepository;

    public List<Requirement> getAllRequirements() {
        return requirementRepository.findAll();
    }

    public Requirement registerRequirement(RequirementRequestDTO requirement) {
        Optional<Job> optionalJob = jobRepository.findById(requirement.jobId());
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            Requirement newRequirement = new Requirement(requirement, job);
            return requirementRepository.save(newRequirement);
        }
        throw new EntityNotFoundException();
    }

}
