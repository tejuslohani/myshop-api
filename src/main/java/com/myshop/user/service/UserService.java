package com.myshop.user.service;

import com.myshop.user.dto.CreateUserRequest;
import com.myshop.user.dto.UserResponse;
import com.myshop.user.entity.User;
import com.myshop.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("User with email " + request.email() + " already exists");
        }
        // Continue with user creation logic
        User user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.firstName(),
                request.lastName(),
                request.phone()
        );
        User saveUser = userRepository.save(user);
        return new UserResponse(
                saveUser.getId(),
                saveUser.getEmail(),
                saveUser.getFirstName(),
                saveUser.getLastName(),
                saveUser.getPhone(),
                saveUser.isActive(),
                saveUser.getCreatedAt()
        );
    }

    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPhone(),
                        user.isActive(),
                        user.getCreatedAt()
                ))
                .toList();
    }


}
