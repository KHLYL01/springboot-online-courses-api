package com.example.alphaapi.model.mapper;

import com.example.alphaapi.model.dto.AddCodeGroupDto;
import com.example.alphaapi.model.dto.AddCourseDto;
import com.example.alphaapi.model.dto.CodeGroupDto;
import com.example.alphaapi.model.dto.CourseDto;
import com.example.alphaapi.model.entity.CodeGroup;
import com.example.alphaapi.model.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeGroupMapper {

    public List<CodeGroupDto> toDtos(List<CodeGroup> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public CodeGroupDto toDto(CodeGroup entity) {
        return CodeGroupDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .courseIds(entity.getCourseIds())
                .numberOfStudent(entity.getNumberOfStudent())
                .price(entity.getPrice())
                .codeNumber(entity.getCodeNumber())
                .enableDate(entity.getEnableDate())
                .build();
    }

    public CodeGroup toEntity(CodeGroupDto dto) {
        return CodeGroup.builder()
                .id(dto.getId())
                .name(dto.getName())
                .courseIds(dto.getCourseIds())
                .numberOfStudent(dto.getNumberOfStudent())
                .price(dto.getPrice())
                .codeNumber(dto.getCodeNumber())
                .enableDate(dto.getEnableDate())
                .build();
    }

    public CodeGroup toAddEntity(AddCodeGroupDto dto){
        return CodeGroup.builder()
                .name(dto.getName())
                .courseIds(dto.getCourseIds())
                .price(dto.getPrice())
                .codeNumber(dto.getCodeNumber())
                .build();
    }
}
