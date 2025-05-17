package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class QuizItemDto {

    private int id;
    private String question;
    private String answer;
    private String cause;
    private String listOfChoices;
    private String language;
    private int quizId;
}
