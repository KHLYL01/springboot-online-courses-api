package com.example.alphaapi.model.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReSendCodeDto {
    private String email;
}
