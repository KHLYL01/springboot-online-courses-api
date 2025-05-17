package com.example.alphaapi.service;

import com.example.alphaapi.model.dto.*;

import java.util.List;


public interface QuizService {

    List<QuizDto> findAllQuiz();

    List<QuizDto> findAllQuizByPartIdForUser(int id);

    List<QuizDto> findAllQuizByPartId(int id);

    void addQuiz(AddQuizDto dto);

    void deleteQuizById(int id);

    void changeVisibility(int id);

}
