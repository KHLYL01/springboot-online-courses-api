package com.example.alphaapi.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class CodeGroupDto {

    private int id;

    private String name;
    private String courseIds;
    private int price;
    private int numberOfStudent;
    private int codeNumber;

    private LocalDateTime enableDate;

}
