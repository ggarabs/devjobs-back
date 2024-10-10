package com.ggarabetti.devjobs_crud.domain.requirements;

import java.util.*;

import jakarta.validation.constraints.NotBlank;

public record RequirementRequestDTO(UUID id, @NotBlank String description, UUID jobId) {
}
