package com.example.demo.mapper;

import com.example.demo.dto.CreateUserRequestDTO;
import com.example.demo.dto.UpdateUserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.model.User;

public class UserMapper {

    public static User toEntity(CreateUserRequestDTO user) {
        return User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getFirstName())
                .password(user.getPassword())
                .email(user.getEmail())
                .gender(user.getGender())
                .role(user.getRole())
                .number(user.getNumber())
                .address(user.getAddress())
                .build();
    }

    public static UserResponseDTO toDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .gender(user.getGender())
                .role(user.getRole())
                .number(user.getNumber())
                .address(user.getAddress())
                .build();
    }
}
