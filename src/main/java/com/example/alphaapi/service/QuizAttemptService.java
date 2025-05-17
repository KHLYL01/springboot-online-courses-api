package com.example.alphaapi.service;

import com.example.alphaapi.model.dto.AddQuizAttemptDto;
import com.example.alphaapi.model.dto.QuizAttemptDto;

import java.util.List;


public interface QuizAttemptService {

    List<QuizAttemptDto> findAllQuizAttempt();

    List<QuizAttemptDto> findAllQuizAttemptByQuizId(int id);

    List<QuizAttemptDto> findAllQuizAttemptByUserId(int userId, int quizId);

    void addQuizAttempt(AddQuizAttemptDto dto);

    void deleteQuizAttemptById(int id);
}
