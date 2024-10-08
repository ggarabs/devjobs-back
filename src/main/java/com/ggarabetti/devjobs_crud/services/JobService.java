package com.ggarabetti.devjobs_crud.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggarabetti.devjobs_crud.domain.job.Job;
import com.ggarabetti.devjobs_crud.domain.job.JobRepository;
import com.ggarabetti.devjobs_crud.domain.job.JobRequestDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    public List<Job> getAllJobs() {
        return repository.findAll();
    }

    public Job registerJob(JobRequestDTO job) {
        Job newJob = new Job(job);
        return repository.save(newJob);
    }

    public Job updateJob(JobRequestDTO data) {
        Optional<Job> job = repository.findById(data.id());
        if (job.isPresent()) {
            Job oldJob = job.get();

            oldJob.setImagePath(data.imagePath());
            oldJob.setMode(data.mode());
            oldJob.setTitle(data.title());
            oldJob.setCompany(data.company());
            oldJob.setCountry(data.country());
            oldJob.setJobDescription(data.jobDescription());
            oldJob.setGeneralRequirements(data.generalRequirements());

            return oldJob;
        }
        throw new EntityNotFoundException();
    }

    public Job removeJob(UUID id) {
        Optional<Job> OptionalJob = repository.findById(id);
        if (OptionalJob.isPresent()) {
            Job job = OptionalJob.get();
            repository.delete(job);
            return job;
        }
        throw new EntityNotFoundException();
    }

}
