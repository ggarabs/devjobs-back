package com.ggarabetti.devjobs_crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggarabetti.devjobs_crud.domain.company.Company;
import com.ggarabetti.devjobs_crud.domain.company.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    public List<Company> getAllCompanies() {
        return repository.findAll();
    }

}
