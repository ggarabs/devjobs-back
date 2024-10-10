package com.ggarabetti.devjobs_crud.domain.requirements;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ggarabetti.devjobs_crud.domain.job.Job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "requirements")
@Entity(name = "requirement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Requirement {
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    @NotNull
    @NotBlank
    private String description;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    public Requirement(RequirementRequestDTO requestRequirement, Job job) {
        this.description = requestRequirement.description();
        this.job = job;
    }

}
