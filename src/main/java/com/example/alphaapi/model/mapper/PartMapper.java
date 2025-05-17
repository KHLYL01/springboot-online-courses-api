package com.example.alphaapi.model.mapper;

import com.example.alphaapi.model.dto.AddPartDto;
import com.example.alphaapi.model.dto.PartDto;
import com.example.alphaapi.model.entity.Part;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartMapper {

    public List<PartDto> toDtos(List<Part> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public PartDto toDto(Part entity) {
        return PartDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .courseId(entity.getCourse().getId())
                .description(entity.getDescription())
                .numberOfStudent(entity.getNumberOfStudent())
                .price(entity.getPrice())
                .valid(entity.isValid())
                .free(entity.isFree())
                .build();
    }

    public Part toEntity(PartDto dto) {
        return Part.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .numberOfStudent(dto.getNumberOfStudent())
                .price(dto.getPrice())
                .valid(dto.isValid())
                .free(dto.isFree())
                .build();
    }

    public Part toAddEntity(AddPartDto dto) {
        return Part.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }
}
