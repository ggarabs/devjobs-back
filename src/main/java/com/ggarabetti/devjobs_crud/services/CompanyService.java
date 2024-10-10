package com.ggarabetti.devjobs_crud.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggarabetti.devjobs_crud.domain.company.Company;
import com.ggarabetti.devjobs_crud.domain.company.CompanyRepository;
import com.ggarabetti.devjobs_crud.domain.company.CompanyRequestDTO;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    public List<Company> getAllCompanies() {
        return repository.findAll();
    }

    public Company registerCompany(CompanyRequestDTO company) {
        Company newCompany = new Company(company);
        return repository.save(newCompany);
    }

}
