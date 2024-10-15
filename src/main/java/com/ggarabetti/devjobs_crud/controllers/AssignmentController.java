package com.ggarabetti.devjobs_crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.ggarabetti.devjobs_crud.domain.assignment.Assignment;
import com.ggarabetti.devjobs_crud.domain.assignment.AssignmentRequestDTO;
import com.ggarabetti.devjobs_crud.services.AssignmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping
    public ResponseEntity getAllAssignments() {
        List<Assignment> allAssignments = assignmentService.getAllAssignments();
        return ResponseEntity.ok(allAssignments);
    }

    @PostMapping
    public ResponseEntity registerAssignment(@RequestBody @Valid AssignmentRequestDTO data) {
        Assignment assignment = assignmentService.registerAssignment(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(assignment);
    }

}
