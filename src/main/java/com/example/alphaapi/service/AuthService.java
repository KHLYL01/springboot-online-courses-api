package com.example.alphaapi.service;

import com.example.alphaapi.model.dto.*;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {

    @Transactional
    void register(UserRegisterDto dto);

    UserDto login(UserLoginDto dto);

    UserTokenDto refresh(UserTokenDto dto);

//    void verificationAccount(UserVerificationDto dto);

//    void sendCode(ReSendCodeDto dto);

//    void changePassword(UserLoginDto dto);
}
