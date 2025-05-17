package com.example.alphaapi.model.mapper;

import com.example.alphaapi.model.dto.PartCodeDto;
import com.example.alphaapi.model.entity.PartCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartCodeMapper {

    public List<PartCodeDto> toDtos(List<PartCode> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public PartCodeDto toDto(PartCode entity) {
        return PartCodeDto.builder()
                .id(entity.getId())
                .symbol(entity.getSymbol())
                .userId(entity.getUser() == null ? 0 : entity.getUser().getId())
                .username(entity.getUser() == null ? "" : entity.getUser().getName())
                .userEmail(entity.getUser() == null ? "" : entity.getUser().getEmail())
                .partId(entity.getPart().getId())
                .paid(entity.isPaid())
                .codeStatus(entity.getCodeStatus())
                .enableDate(entity.getEnableDate())
                .build();
    }

    public PartCode toEntity(PartCodeDto dto) {
        return PartCode.builder()
                .id(dto.getId())
                .symbol(dto.getSymbol())
                .paid(dto.isPaid())
                .codeStatus(dto.getCodeStatus())
                .enableDate(dto.getEnableDate())
                .build();
    }

}
