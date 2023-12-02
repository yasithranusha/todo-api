package com.yasith.todo.service;

import com.yasith.todo.dto.LoginDto;
import com.yasith.todo.dto.RegisterDto;

public interface AuthService{
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
