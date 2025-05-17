package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class PartDto {

    private  int id;

    private int courseId;

    private String name;
    private String description;
    private int numberOfStudent;
    private int price;
    private boolean valid;
    private boolean free;


}
