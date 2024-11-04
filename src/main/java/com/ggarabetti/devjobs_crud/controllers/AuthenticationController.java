package com.ggarabetti.devjobs_crud.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggarabetti.devjobs_crud.domain.user.User;
import com.ggarabetti.devjobs_crud.domain.role.Role;
import com.ggarabetti.devjobs_crud.domain.role.RoleRepository;
import com.ggarabetti.devjobs_crud.domain.user.AuthenticationDTO;
import com.ggarabetti.devjobs_crud.domain.user.RegisterDTO;
import com.ggarabetti.devjobs_crud.domain.user.UserRepository;

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
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
