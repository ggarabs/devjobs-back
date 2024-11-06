package com.ggarabetti.devjobs_crud.application.dto;

import java.util.Set;

public record RegisterDTO(String login, String password, Set<String> roles) {
}
