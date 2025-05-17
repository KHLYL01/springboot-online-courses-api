package com.example.alphaapi.model.mapper;

import com.example.alphaapi.model.dto.AddQuizDto;
import com.example.alphaapi.model.dto.QuizDto;
import com.example.alphaapi.model.entity.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizMapper {

    public List<QuizDto> toDtos(List<Quiz> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public QuizDto toDto(Quiz entity) {
        return QuizDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .totalScore(entity.getTotalScore())
                .partId(entity.getPart().getId())
                .valid(entity.isValid())
                .build();
    }

    public Quiz toEntity(QuizDto dto) {
        return Quiz.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .totalScore(dto.getTotalScore())
                .valid(dto.isValid())
                .build();
    }

    public Quiz toAddEntity(AddQuizDto dto) {
        return Quiz.builder()
                .title(dto.getTitle())
                .totalScore(dto.getTotalScore())
                .build();
    }
}
