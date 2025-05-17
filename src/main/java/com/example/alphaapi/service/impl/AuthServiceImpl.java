package com.example.alphaapi.service.impl;

import com.example.alphaapi.config.JwtService;
import com.example.alphaapi.model.dto.*;
import com.example.alphaapi.model.entity.User;
import com.example.alphaapi.model.mapper.UserMapper;
import com.example.alphaapi.repo.UserRepo;
import com.example.alphaapi.service.AuthService;
import com.example.alphaapi.service.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;

    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final SendMailService sendMailService;

    private final PasswordEncoder passwordEncoder;


    @Override
    public void register(UserRegisterDto dto) {

//        String verificationCode = RandomStringUtils.randomNumeric(6);
//        sendMailService.sendMail(dto.getEmail(), verificationCode, "confirm your account");

        User user = userMapper.toEntity(dto);

//        user.setEnabled(false);
//        user.setVerificationCode(verificationCode);

        user.setEnabled(true);
        userRepo.save(user);

    }

    @Override
    public UserDto login(UserLoginDto dto) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        User user = userRepo.findByEmail(dto.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        UserDto userDto = userMapper.toDto(user);
        userDto.setToken(jwt);
        userDto.setRefreshToken(refreshToken);

        return userDto;
    }

    @Override
    public UserTokenDto refresh(UserTokenDto dto) {
        String username = jwtService.extractUsername(dto.getRefreshToken());
        User user = userRepo.findByEmail(username).orElseThrow();
        if (jwtService.isTokenValid(dto.getRefreshToken(), user)) {
            String jwt = jwtService.generateToken(user);

            return UserTokenDto.builder()
                    .token(jwt)
                    .refreshToken(dto.getRefreshToken())
                    .build();
        }
        return null;
    }

//    @Override
//    public void verificationAccount(UserVerificationDto dto) {
//        User user = userRepo.findByEmail(dto.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email"));
//
//        if (user.getVerificationCode().equals(dto.getCode())) {
//            user.setEnabled(true);
//            userRepo.save(user);
//        } else {
//            throw new IllegalArgumentException("Invalid code");
//        }
//
//    }

//    @Override
//    public void sendCode(ReSendCodeDto dto) {
//        String verificationCode = RandomStringUtils.randomNumeric(6);
//
//        sendMailService.sendMail(dto.getEmail(), verificationCode, "confirm your account");
//        User user = userRepo.findByEmail(dto.getEmail()).get();
//        user.setVerificationCode(verificationCode);
//
//        userRepo.save(user);
//    }

//    @Override
//    public void changePassword(UserLoginDto dto) {
//        System.out.println(dto.getEmail());
//        User user = userRepo.findByEmail(dto.getEmail()).get();
//
//        user.setPassword(passwordEncoder.encode(dto.getPassword()));
//        userRepo.save(user);
//    }

}
