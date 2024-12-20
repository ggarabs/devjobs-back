package com.ggarabetti.devjobs_crud.interfaces.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.ggarabetti.devjobs_crud.application.dto.AssignmentRequestDTO;
import com.ggarabetti.devjobs_crud.application.service.AssignmentService;
import com.ggarabetti.devjobs_crud.domain.model.Assignment;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        List<Assignment> allAssignments = assignmentService.getAllAssignments();
        return ResponseEntity.ok(allAssignments);
    }

    @PostMapping
    public ResponseEntity<Assignment> registerAssignment(@RequestBody @Valid AssignmentRequestDTO data) {
        Assignment assignment = assignmentService.registerAssignment(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(assignment);
    }

}
