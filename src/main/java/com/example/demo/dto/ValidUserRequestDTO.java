package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public record ValidUserRequestDTO(
        @NotNull String email,
        @NotNull String password
) {
}
