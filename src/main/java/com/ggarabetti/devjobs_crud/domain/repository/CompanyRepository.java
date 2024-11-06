package com.ggarabetti.devjobs_crud.domain.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ggarabetti.devjobs_crud.domain.model.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    List<Company> findAll();
}
