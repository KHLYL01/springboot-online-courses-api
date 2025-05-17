package com.example.alphaapi.model.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@Builder
public class AddPdfFileDto {

    private int partId;
    private String title;
    private MultipartFile file;
}
