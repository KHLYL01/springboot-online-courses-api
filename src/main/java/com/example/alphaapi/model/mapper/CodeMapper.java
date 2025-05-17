package com.example.alphaapi.model.mapper;

import com.example.alphaapi.model.dto.CodeDto;
import com.example.alphaapi.model.dto.PartCodeDto;
import com.example.alphaapi.model.entity.Code;
import com.example.alphaapi.model.entity.PartCode;
import com.example.alphaapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeMapper {
    
    public List<CodeDto> toDtos(List<Code> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public CodeDto toDto(Code entity) {
        return CodeDto.builder()
                .id(entity.getId())
                .symbol(entity.getSymbol())
                .userId(entity.getUser() == null ? 0 : entity.getUser().getId())
                .username(entity.getUser() == null ? "" : entity.getUser().getName())
                .userEmail(entity.getUser() == null ? "" : entity.getUser().getEmail())
                .codeGroupId(entity.getCodeGroup().getId())
                .codeGroupCourseIds(entity.getCodeGroup().getCourseIds())
                .codeStatus(entity.getCodeStatus())
                .enableDate(entity.getEnableDate())
                .build();
    }

    public Code toEntity(CodeDto dto) {
        return Code.builder()
                .id(dto.getId())
                .symbol(dto.getSymbol())
                .codeStatus(dto.getCodeStatus())
                .enableDate(dto.getEnableDate())
                .build();
    }

}
