package com.ggarabetti.devjobs_crud.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggarabetti.devjobs_crud.domain.job.Job;
import com.ggarabetti.devjobs_crud.domain.job.JobRequestDTO;
import com.ggarabetti.devjobs_crud.services.JobService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> allJobs = jobService.getAllJobs();
        return ResponseEntity.ok(allJobs);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Job> registerJob(@RequestBody @Valid JobRequestDTO data) {
        Job job = jobService.registerJob(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(job);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping()
    @Transactional
    public ResponseEntity<Job> updateJob(@RequestBody @Valid JobRequestDTO data) {
        var updatedJob = jobService.updateJob(data);
        return ResponseEntity.ok(updatedJob);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Job> removeJob(@PathVariable UUID id) {
        var removedJob = jobService.removeJob(id);
        return ResponseEntity.ok(removedJob);
    }

}
