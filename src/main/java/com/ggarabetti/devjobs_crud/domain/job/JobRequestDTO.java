package com.ggarabetti.devjobs_crud.domain.job;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JobRequestDTO(UUID id, @NotBlank String mode, @NotBlank String title,
        @NotBlank String country, @NotBlank String jobDescription,
        @NotBlank String generalRequirements, @NotBlank String generalAssignments, @NotNull UUID companyId) {
}
