package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class AddAllQuizItemDto {

    private List<AddQuizItemDto> quizItems;

}
