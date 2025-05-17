package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ActiveCodeDto {

    private String symbol;
    private String macDeviceCode;
    private int userId;
    private boolean isCourse;

}
