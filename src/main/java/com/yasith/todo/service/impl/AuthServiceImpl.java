package com.yasith.todo.service.impl;

import com.yasith.todo.dto.RegisterDto;
import com.yasith.todo.entity.Role;
import com.yasith.todo.entity.User;
import com.yasith.todo.exception.TodoAPIException;
import com.yasith.todo.repository.RoleRepository;
import com.yasith.todo.repository.UserRepository;
import com.yasith.todo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public String register(RegisterDto registerDto) {
        //Check username exist
        if (userRepository.existsByUserName(registerDto.getUsername())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST,"Username already exists.");
        }

        //Check email exist
        if (userRepository.existsByEmail(registerDto.getEmail())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST,"Email already exist.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setUserName(registerDto.getUsername());
        user.setPassword("{bcrypt}" + passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered successfully!";
    }
}
