package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddQuizAttemptDto {

    private int quizId;

    private int userId;

    private String listOfAnswers;

    private int score;

    private int correctAnswers;
}
