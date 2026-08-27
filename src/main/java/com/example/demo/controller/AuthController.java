package com.example.demo.controller;

import com.example.demo.dto.ValidUserRequestDTO;
import com.example.demo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/is-valid-user")
    public ResponseEntity<?> isValidUser(@RequestBody ValidUserRequestDTO validUserRequestDTO) {
        try {
            return ResponseEntity.ok(this.authService.isValidUser(validUserRequestDTO));
        } catch (Exception e){
            log.error("Error while validating user with email: {}", validUserRequestDTO.email(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(false);

        }
    }
}
