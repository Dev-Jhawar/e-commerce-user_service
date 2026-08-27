package com.example.demo.dto;

import com.example.demo.enums.Gender;
import com.example.demo.enums.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Gender gender;
    private Role role;
    private String number;
    private String address;

}
