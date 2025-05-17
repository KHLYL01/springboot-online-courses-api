package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class QuizAttemptDto {

    private int id;

    private int quizId;

    private int userId;

    private String userName;

    private String listOfAnswers;

    private int score;

    private int correctAnswers;

    private String date;

}
