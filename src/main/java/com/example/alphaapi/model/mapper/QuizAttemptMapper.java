package com.example.alphaapi.model.mapper;

import com.example.alphaapi.model.dto.AddQuizAttemptDto;
import com.example.alphaapi.model.dto.QuizAttemptDto;
import com.example.alphaapi.model.entity.QuizAttempt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizAttemptMapper {

    public List<QuizAttemptDto> toDtos(List<QuizAttempt> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public QuizAttemptDto toDto(QuizAttempt entity) {
        return QuizAttemptDto.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .userName(entity.getUser().getName())
                .quizId(entity.getQuiz().getId())
                .score(entity.getScore())
                .listOfAnswers(entity.getListOfAnswers())
                .correctAnswers(entity.getCorrectAnswers())
                .date(entity.getDate().toString().substring(0,19))
                .build();
    }

    public QuizAttempt toEntity(QuizAttemptDto dto) {
        return QuizAttempt.builder()
                .id(dto.getId())
                .score(dto.getScore())
                .listOfAnswers(dto.getListOfAnswers())
                .correctAnswers(dto.getCorrectAnswers())
                .build();
    }

    public QuizAttempt toAddEntity(AddQuizAttemptDto dto) {
        return QuizAttempt.builder()
                .score(dto.getScore())
                .listOfAnswers(dto.getListOfAnswers())
                .correctAnswers(dto.getCorrectAnswers())
                .build();
    }
}
