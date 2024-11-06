package com.ggarabetti.devjobs_crud.domain.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ggarabetti.devjobs_crud.application.dto.CompanyRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "companies")
@Entity(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonIgnore
    private UUID id;

    @Column(name = "company_name", nullable = false)
    private String name;

    private String website;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "large_image_path")
    private String largeImagePath;

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobVacancies;

    public Company(CompanyRequestDTO data, List<Job> vacancies) {
        this.name = data.name();
        this.website = data.website();
        this.imagePath = data.imagePath();
        this.largeImagePath = data.largeImagePath();
        this.jobVacancies = vacancies;
    }

}
