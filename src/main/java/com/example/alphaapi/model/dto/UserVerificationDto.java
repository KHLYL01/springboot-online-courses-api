package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class UserVerificationDto {
    private String email;
    private String password;
    private String code;
}
