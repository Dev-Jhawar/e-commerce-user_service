package com.example.demo.serviceImpl;

import com.example.demo.dto.CreateUserRequestDTO;
import com.example.demo.dto.UpdateUserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo repo;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        log.info("Fetching all users");

        List<UserResponseDTO> users = repo.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());

        log.info("Successfully fetched {} users", users.size());

        return users;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        log.info("Fetching user with id: {}", id);

        User user = repo.findById(id)
                .orElseThrow(() -> {
                    log.warn("User not found with id: {}", id);
                    return new ResourceNotFoundException("User not Found.");
                });

        log.info("User found with id: {}", id);

        return UserMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO createUser(CreateUserRequestDTO createUser) {
        log.info("Creating a new user");

        User user = UserMapper.toEntity(createUser);
        User savedUser = repo.save(user);

        log.info("User created successfully with id: {}", savedUser.getId());

        return UserMapper.toDTO(savedUser);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UpdateUserRequestDTO updateDetails) {
        log.info("Updating user with id: {}", id);

        User user = repo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Cannot update. User not found with id: {}", id);
                    return new ResourceNotFoundException("Invalid ID is Provided.");
                });

        user.setFirstName(Optional.ofNullable(updateDetails.getFirstName()).orElse(user.getFirstName()));
        user.setLastName(Optional.ofNullable(updateDetails.getLastName()).orElse(user.getLastName()));
        user.setEmail(Optional.ofNullable(updateDetails.getEmail()).orElse(user.getEmail()));
        user.setGender(Optional.ofNullable(updateDetails.getGender()).orElse(user.getGender()));
        user.setRole(Optional.ofNullable(updateDetails.getRole()).orElse(user.getRole()));
        user.setNumber(Optional.ofNullable(updateDetails.getNumber()).orElse(user.getNumber()));
        user.setAddress(Optional.ofNullable(updateDetails.getAddress()).orElse(user.getAddress()));


        User updatedUser = repo.save(user);

        log.info("User updated successfully with id: {}", id);

        return UserMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);

        repo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Cannot delete. User not found with id: {}", id);
                    return new ResourceNotFoundException("Invalid ID is Provided.");
                });

        repo.deleteById(id);

        log.info("User deleted successfully with id: {}", id);
    }
}
