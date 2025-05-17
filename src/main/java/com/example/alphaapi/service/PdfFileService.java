package com.example.alphaapi.service;

import com.example.alphaapi.model.dto.*;
import com.example.alphaapi.model.entity.PdfFile;

import java.io.IOException;
import java.util.List;


public interface PdfFileService {

    List<PdfFileDto> findAllPdfFile();

    List<PdfFileDto> findAllPdfFileByPartId(int id);

    void addPdfFile(AddPdfFileDto dto) throws IOException;

//    void updatePdfFile(UpdatePdfFileDto dto, int id);

    void deletePdfFileById(String id);

    PdfFile findById(String id);
}
