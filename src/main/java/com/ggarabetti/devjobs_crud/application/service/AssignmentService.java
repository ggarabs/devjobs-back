package com.ggarabetti.devjobs_crud.application.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggarabetti.devjobs_crud.application.dto.AssignmentRequestDTO;
import com.ggarabetti.devjobs_crud.domain.model.Assignment;
import com.ggarabetti.devjobs_crud.domain.model.Job;
import com.ggarabetti.devjobs_crud.domain.repository.AssignmentRepository;
import com.ggarabetti.devjobs_crud.domain.repository.JobRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private JobRepository jobRepository;

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Assignment registerAssignment(AssignmentRequestDTO assignment) {
        Optional<Job> optionalJob = jobRepository.findById(assignment.jobId());
        if (optionalJob.isPresent()) {
            Job newJob = optionalJob.get();
            Assignment newAssignment = new Assignment(assignment, newJob);
            return assignmentRepository.save(newAssignment);
        }
        throw new EntityNotFoundException();
    }

}
