package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LessonDto {

    private String id;
    private String title;
    private String videoUrl;
    private int partId;

}
