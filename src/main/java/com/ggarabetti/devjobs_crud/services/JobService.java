package com.ggarabetti.devjobs_crud.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggarabetti.devjobs_crud.domain.assignment.Assignment;
import com.ggarabetti.devjobs_crud.domain.assignment.AssignmentRepository;
import com.ggarabetti.devjobs_crud.domain.company.Company;
import com.ggarabetti.devjobs_crud.domain.company.CompanyRepository;
import com.ggarabetti.devjobs_crud.domain.job.Job;
import com.ggarabetti.devjobs_crud.domain.job.JobRepository;
import com.ggarabetti.devjobs_crud.domain.job.JobRequestDTO;
import com.ggarabetti.devjobs_crud.domain.requirements.Requirement;
import com.ggarabetti.devjobs_crud.domain.requirements.RequirementRepository;

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
