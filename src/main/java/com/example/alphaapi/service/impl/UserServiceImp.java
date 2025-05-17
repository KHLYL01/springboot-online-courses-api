package com.example.alphaapi.service.impl;

import com.example.alphaapi.repo.UserRepo;
import com.example.alphaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepo userRepo;


    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepo.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found")
        );
    }

    @Override
    public long countAllUser() {
        return userRepo.countAllByEnabledIsTrue();
    }

}
