package com.ggarabetti.devjobs_crud.interfaces.web.controller;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
import com.ggarabetti.devjobs_crud.infrastructure.service.TokenService;

import jakarta.servlet.http.HttpServletResponse;
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

    @Autowired
    private TokenService tokenService;

    @CrossOrigin(origins = "http://localhost:5173/*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data, HttpServletResponse resp) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            ResponseCookie httpOnlyCookie = ResponseCookie.from("token", token).httpOnly(true).secure(false)
                    .sameSite("Strict").path("/")
                    .maxAge(30 * 60)
                    .build();

            resp.setHeader("Set-Cookie", httpOnlyCookie.toString());

            return ResponseEntity.ok(Map.of("message", "Authentication succesful"));
        } catch (AuthenticationException err) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Unauthorized", "message", "Bad Credentials"));
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal Server Error", "message", "Try again later"));
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
