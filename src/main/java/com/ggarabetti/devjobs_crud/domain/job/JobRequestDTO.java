package com.ggarabetti.devjobs_crud.domain.job;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record JobRequestDTO(UUID id, @NotBlank String imagePath, @NotBlank String mode, @NotBlank String title,
                @NotBlank String company, @NotBlank String country, @NotBlank String jobDescription,
                @NotBlank String generalRequirements, @NotBlank String generalAssignments) {
}
