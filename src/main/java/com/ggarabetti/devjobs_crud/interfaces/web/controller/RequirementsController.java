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

import com.ggarabetti.devjobs_crud.application.dto.RequirementRequestDTO;
import com.ggarabetti.devjobs_crud.application.service.RequirementService;
import com.ggarabetti.devjobs_crud.domain.model.Requirement;

import jakarta.validation.Valid;

@RestController
@RequestMapping("requirements")
public class RequirementsController {

    @Autowired
    private RequirementService requirementsService;

    @GetMapping
    public ResponseEntity<List<Requirement>> getAllRequirements() {
        List<Requirement> allRequirements = requirementsService.getAllRequirements();
        return ResponseEntity.ok(allRequirements);
    }

    @PostMapping
    public ResponseEntity<Requirement> registerRequirement(@RequestBody @Valid RequirementRequestDTO data) {
        Requirement requirement = requirementsService.registerRequirement(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(requirement);
    }

}
