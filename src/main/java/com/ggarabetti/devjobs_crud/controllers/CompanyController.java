package com.ggarabetti.devjobs_crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggarabetti.devjobs_crud.domain.company.Company;
import com.ggarabetti.devjobs_crud.domain.company.CompanyRequestDTO;
import com.ggarabetti.devjobs_crud.services.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> allCompanies = companyService.getAllCompanies();
        return ResponseEntity.ok(allCompanies);
    }

    @PostMapping
    public ResponseEntity<Company> registerCompany(@RequestBody @Valid CompanyRequestDTO data) {
        Company company = companyService.registerCompany(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

}
