package com.ggarabetti.devjobs_crud.domain.job;

import java.time.LocalDateTime;
import java.util.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ggarabetti.devjobs_crud.domain.company.Company;
import com.ggarabetti.devjobs_crud.domain.requirements.Requirement;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "job")
@Entity(name = "job")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonIgnore
    private UUID id;

    @NotNull
    private String mode;

    @NotNull
    private String title;

    @NotNull
    private String country;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

    @JsonManagedReference
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Requirement> requirements;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "general_requirements")
    private String generalRequirements;

    @Column(name = "general_assignments")
    private String generalAssignments;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public Job(JobRequestDTO requestJob, Company company, List<Requirement> requirements) {
        this.mode = requestJob.mode();
        this.title = requestJob.title();
        this.country = requestJob.country();
        this.jobDescription = requestJob.jobDescription();
        this.generalRequirements = requestJob.generalRequirements();
        this.generalAssignments = requestJob.generalAssignments();
        this.company = company;
        this.requirements = requirements;
    }
}
