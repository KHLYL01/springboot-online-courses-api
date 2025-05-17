package com.example.alphaapi.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

//    List<UserDto> findAllUser();
    UserDetailsService userDetailsService();

    long countAllUser();
}
