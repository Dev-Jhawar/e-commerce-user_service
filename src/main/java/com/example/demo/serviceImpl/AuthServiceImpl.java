package com.example.demo.serviceImpl;

import com.example.demo.dto.ValidUserRequestDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;

    @Override
    public boolean isValidUser(ValidUserRequestDTO validUserRequest) {
        Optional<User> userOptional = this.userRepo.findUserByEmail(validUserRequest.email());

        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();
        return validUserRequest.password().equals(user.getPassword());
    }
}
