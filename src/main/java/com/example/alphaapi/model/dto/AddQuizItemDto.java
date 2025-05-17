package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddQuizItemDto {

    private String question;
    private String answer;
    private String cause;
    private String listOfChoices;

    private String language;
    private int quizId;

}
