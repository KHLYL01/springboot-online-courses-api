package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddAllPartCodeDto {

    private boolean paid;
    private int partId;
    private int number;

}
