package com.ggarabetti.devjobs_crud.domain.company;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    List<Company> findAll();
}
