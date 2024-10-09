package com.ggarabetti.devjobs_crud.domain.company;

import java.util.*;
import jakarta.validation.constraints.NotBlank;

public record CompanyRequestDTO(UUID id, @NotBlank String name, @NotBlank String website, @NotBlank String imagePath,
        @NotBlank String largeImagePath) {
}
