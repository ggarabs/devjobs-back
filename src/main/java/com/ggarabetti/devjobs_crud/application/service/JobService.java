package com.ggarabetti.devjobs_crud.application.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggarabetti.devjobs_crud.application.dto.JobRequestDTO;
import com.ggarabetti.devjobs_crud.domain.model.Assignment;
import com.ggarabetti.devjobs_crud.domain.model.Company;
import com.ggarabetti.devjobs_crud.domain.model.Job;
import com.ggarabetti.devjobs_crud.domain.model.Requirement;
import com.ggarabetti.devjobs_crud.domain.repository.AssignmentRepository;
import com.ggarabetti.devjobs_crud.domain.repository.CompanyRepository;
import com.ggarabetti.devjobs_crud.domain.repository.JobRepository;
import com.ggarabetti.devjobs_crud.domain.repository.RequirementRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job registerJob(JobRequestDTO job) {
        Optional<Company> optionalCompany = companyRepository.findById(job.companyId());
        Optional<List<Requirement>> optionalRequirements = requirementRepository.findAllByJobId(job.id());
        Optional<List<Assignment>> optionalAssignments = assignmentRepository.findAllByJobId(job.id());
        if (optionalCompany.isPresent() && optionalRequirements.isPresent()) {
            Company company = optionalCompany.get();
            List<Requirement> requirements = optionalRequirements.get();
            List<Assignment> assignments = optionalAssignments.get();
            Job newJob = new Job(job, company, requirements, assignments);
            return jobRepository.save(newJob);
        }
        throw new EntityNotFoundException();
    }

    public Job updateJob(JobRequestDTO data) {
        Optional<Job> optionalJob = jobRepository.findById(data.id());
        Optional<Company> optionalCompany = companyRepository.findById(data.companyId());
        if (optionalJob.isPresent() && optionalCompany.isPresent()) {
            Job newJob = optionalJob.get();
            Company newCompany = optionalCompany.get();

            newJob.setMode(data.mode());
            newJob.setTitle(data.title());
            newJob.setCountry(data.country());
            newJob.setJobDescription(data.jobDescription());
            newJob.setGeneralRequirements(data.generalRequirements());
            newJob.setGeneralAssignments(data.generalAssignments());
            newJob.setCompany(newCompany);

            return newJob;
        }
        throw new EntityNotFoundException();
    }

    public Job removeJob(UUID id) {
        Optional<Job> OptionalJob = jobRepository.findById(id);
        if (OptionalJob.isPresent()) {
            Job job = OptionalJob.get();
            jobRepository.delete(job);
            return job;
        }
        throw new EntityNotFoundException();
    }

}
