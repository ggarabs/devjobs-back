package com.ggarabetti.devjobs_crud.domain.assignment;

import java.util.*;

import jakarta.validation.constraints.NotBlank;

public record AssignmentRequestDTO(UUID id, @NotBlank String description, UUID jobId) {
}
