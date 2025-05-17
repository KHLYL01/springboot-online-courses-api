package com.example.alphaapi.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCodeGroupDto {
    private String name;
    private int price;
    private String courseIds;
    private int codeNumber;
}
