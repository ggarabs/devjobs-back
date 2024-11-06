package com.ggarabetti.devjobs_crud.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.ggarabetti.devjobs_crud.domain.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetails findByLogin(String login);
}
