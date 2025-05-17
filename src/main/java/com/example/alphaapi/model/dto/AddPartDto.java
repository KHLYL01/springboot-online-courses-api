package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class AddPartDto {

    private int courseId;
    private String name;
    private String description;
    private int price;
}
