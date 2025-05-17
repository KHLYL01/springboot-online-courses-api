package com.example.alphaapi.service;

import com.example.alphaapi.model.dto.AddQuizItemDto;
import com.example.alphaapi.model.dto.QuizItemDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface QuizItemService {

    List<QuizItemDto> findAllQuizItem();

    List<QuizItemDto> findAllQuizItemByQuizId(int id);

    void addQuizItem(AddQuizItemDto dto);

    @Transactional
    void addAllQuizItem(List<AddQuizItemDto> dtos);

    void deleteQuizItemById(int id);

}
