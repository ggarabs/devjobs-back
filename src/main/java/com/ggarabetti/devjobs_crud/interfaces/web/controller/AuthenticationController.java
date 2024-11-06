package com.ggarabetti.devjobs_crud.interfaces.web.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggarabetti.devjobs_crud.application.dto.AuthenticationDTO;
import com.ggarabetti.devjobs_crud.application.dto.RegisterDTO;
import com.ggarabetti.devjobs_crud.domain.model.user.Role;
import com.ggarabetti.devjobs_crud.domain.model.user.User;
import com.ggarabetti.devjobs_crud.domain.repository.RoleRepository;
import com.ggarabetti.devjobs_crud.domain.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            System.out.println("Authentication Success!");

            return ResponseEntity.ok().body("Authentication Success!");
        } catch (AuthenticationException err) {
            System.out.println("Bad Credentials");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials");
        } catch (Exception err) {
            System.out.println("Internal error");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error. Try again");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        Set<Role> userRoles = new HashSet<>();

        for (String role : data.roles()) {
            Role currRole = roleRepository.findByName(role);
            if (currRole == null)
                return ResponseEntity.badRequest().body("Role " + role + " não encontrado!");
            userRoles.add(currRole);
        }

        User newUser = new User(data.login(), encryptedPassword, userRoles);

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
