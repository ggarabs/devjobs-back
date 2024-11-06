package com.ggarabetti.devjobs_crud.application.dto;

import java.util.*;

import jakarta.validation.constraints.NotBlank;

public record AssignmentRequestDTO(UUID id, @NotBlank String description, UUID jobId) {
}
