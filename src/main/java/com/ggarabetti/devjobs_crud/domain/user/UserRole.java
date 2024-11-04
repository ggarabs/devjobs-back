package com.ggarabetti.devjobs_crud.domain.user;

public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    RECRUITER("ROLE_RECRUITER"),
    CANDIDATE("ROLE_CANDIDATE"),
    GUEST("ROLE_GUEST");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
