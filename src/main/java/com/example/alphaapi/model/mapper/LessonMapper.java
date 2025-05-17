package com.example.alphaapi.model.mapper;

import com.example.alphaapi.model.dto.AddLessonDto;
import com.example.alphaapi.model.dto.LessonDto;
import com.example.alphaapi.model.entity.Lesson;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonMapper {

    public List<LessonDto> toDtos(List<Lesson> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public LessonDto toDto(Lesson entity) {
        return LessonDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .videoUrl(entity.getVideoUrl())
                .partId(entity.getPart().getId())
                .build();
    }

    public Lesson toEntity(LessonDto dto) {
        return Lesson.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .videoUrl(dto.getVideoUrl())
                .build();
    }

//    public Lesson toAddEntity(AddLessonDto dto) {
//        return Lesson.builder()
//                .title(dto.getTitle())
//                .videoUrl(dto.getVideoUrl())
//                .build();
//    }
}
