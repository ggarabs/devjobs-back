package com.ggarabetti.devjobs_crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.ggarabetti.devjobs_crud.domain.job.Job;
import com.ggarabetti.devjobs_crud.domain.job.JobRepository;
import com.ggarabetti.devjobs_crud.domain.requirements.Requirement;
import com.ggarabetti.devjobs_crud.domain.requirements.RequirementRepository;
import com.ggarabetti.devjobs_crud.domain.requirements.RequirementRequestDTO;

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
