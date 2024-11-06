package com.ggarabetti.devjobs_crud.application.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggarabetti.devjobs_crud.application.dto.CompanyRequestDTO;
import com.ggarabetti.devjobs_crud.domain.model.Company;
import com.ggarabetti.devjobs_crud.domain.model.Job;
import com.ggarabetti.devjobs_crud.domain.repository.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    public List<Company> getAllCompanies() {
        return repository.findAll();
    }

    public Company registerCompany(CompanyRequestDTO company) {
        List<Job> vacancies = new ArrayList<>();
        Company newCompany = new Company(company, vacancies);
        return repository.save(newCompany);
    }

}
