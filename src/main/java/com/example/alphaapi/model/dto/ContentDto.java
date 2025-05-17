package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class ContentDto {

    private List<LessonDto> lessons;
    private List<PdfFileDto> files;
    private List<QuizDto> quizzes;

}
