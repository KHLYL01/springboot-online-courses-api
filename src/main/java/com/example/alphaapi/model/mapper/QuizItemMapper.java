package com.example.alphaapi.model.mapper;

import com.example.alphaapi.model.dto.AddQuizItemDto;
import com.example.alphaapi.model.dto.QuizItemDto;
import com.example.alphaapi.model.entity.QuizItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizItemMapper {

    public List<QuizItemDto> toDtos(List<QuizItem> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public QuizItemDto toDto(QuizItem entity) {
        return QuizItemDto.builder()
                .id(entity.getId())
                .question(entity.getQuestion())
                .answer(entity.getAnswer())
                .cause(entity.getCause())
                .listOfChoices(entity.getListOfChoices())
                .quizId(entity.getQuiz().getId())
                .language(entity.getLanguage())
                .build();
    }

    public QuizItem toEntity(QuizItemDto dto) {
        return QuizItem.builder()
                .id(dto.getId())
                .question(dto.getQuestion())
                .answer(dto.getAnswer())
                .cause(dto.getCause())
                .listOfChoices(dto.getListOfChoices())
                .language(dto.getLanguage())
                .build();
    }

    public QuizItem toAddEntity(AddQuizItemDto dto) {
        return QuizItem.builder()
                .question(dto.getQuestion())
                .answer(dto.getAnswer())
                .cause(dto.getCause())
                .listOfChoices(dto.getListOfChoices())
                .language(dto.getLanguage())
                .build();
    }
}
