package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddCourseDto {

    private String name;
    private String teacherName;
    private int price;

}
