package com.ggarabetti.devjobs_crud.domain.job;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private UUID id;

    private String mode;

    private String title;

    private String country;

    @Column(name = "company_id", nullable = false)
    private UUID company;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "general_requirements")
    private String generalRequirements;

    @Column(name = "general_assignments")
    private String generalAssignments;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public Job(JobRequestDTO requestJob) {
        this.mode = requestJob.mode();
        this.title = requestJob.title();
        this.country = requestJob.country();
        this.jobDescription = requestJob.jobDescription();
        this.generalRequirements = requestJob.generalRequirements();
        this.generalAssignments = requestJob.generalAssignments();
        this.company = requestJob.companyId();
    }
}
