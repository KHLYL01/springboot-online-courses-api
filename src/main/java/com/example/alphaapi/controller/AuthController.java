package com.example.alphaapi.controller;

import com.example.alphaapi.model.dto.*;
import com.example.alphaapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    final private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterDto dto) {
        authService.register(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody UserTokenDto dto) {
        return ResponseEntity.ok(authService.refresh(dto));
    }

//    @PostMapping("/code")
//    public ResponseEntity<?> reSendCode(@RequestBody ReSendCodeDto dto) {
//        authService.sendCode(dto);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/verification")
//    public ResponseEntity<?> verification(@RequestBody UserVerificationDto dto) {
//        authService.verificationAccount(dto);
//        if (!Objects.equals(dto.getPassword(), "")) {
//            return ResponseEntity.ok(authService.login(UserLoginDto.builder().email(dto.getEmail()).password(dto.getPassword()).build()));
//        }
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PostMapping("/change")
//    public ResponseEntity<?> changePassword(@RequestBody UserLoginDto dto) {
//        authService.changePassword(dto);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

}
