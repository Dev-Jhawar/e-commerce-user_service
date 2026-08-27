package com.example.demo.service;

import com.example.demo.dto.ValidUserRequestDTO;

public interface AuthService {

    boolean isValidUser(ValidUserRequestDTO validUserRequest);

}
