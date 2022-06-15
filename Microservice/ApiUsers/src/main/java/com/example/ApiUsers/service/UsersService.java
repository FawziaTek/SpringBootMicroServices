package com.example.ApiUsers.service;

import com.example.ApiUsers.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserDetailByEmail(String email);
}