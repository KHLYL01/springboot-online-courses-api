package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class AddQuizDto {

    private String title;
    private int totalScore;
    private int partId;

}
