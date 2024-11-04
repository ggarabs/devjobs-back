package com.ggarabetti.devjobs_crud.domain.user;

import java.util.Set;

public record RegisterDTO(String login, String password, Set<String> roles) {
}
