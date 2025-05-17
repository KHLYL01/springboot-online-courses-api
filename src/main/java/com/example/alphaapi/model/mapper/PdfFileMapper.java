package com.example.alphaapi.model.mapper;

import com.example.alphaapi.model.dto.AddPdfFileDto;
import com.example.alphaapi.model.dto.PdfFileDto;
import com.example.alphaapi.model.entity.PdfFile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PdfFileMapper {

    public List<PdfFileDto> toDtos(List<PdfFile> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public PdfFileDto toDto(PdfFile entity) {
        return PdfFileDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .partId(entity.getPart().getId())
                .build();
    }

}
