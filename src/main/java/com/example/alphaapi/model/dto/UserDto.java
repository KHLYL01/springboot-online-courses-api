package com.example.alphaapi.model.dto;

import com.example.alphaapi.model.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class UserDto {
    private int id;
    private String name;
    private String email;
    private Role role;
    private String token;
    private String refreshToken;
}
