package com.example.alphaapi.service.impl;

import com.example.alphaapi.model.dto.*;
import com.example.alphaapi.model.entity.Part;
import com.example.alphaapi.model.entity.PdfFile;
import com.example.alphaapi.model.mapper.PdfFileMapper;
import com.example.alphaapi.repo.PartRepo;
import com.example.alphaapi.repo.PdfFileRepo;
import com.example.alphaapi.service.PdfFileService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PdfFileServiceImp implements PdfFileService {

    private final PartRepo partRepo;

    private final PdfFileRepo pdfFileRepo;

    private final PdfFileMapper pdfFileMapper;

    @Value("${file.path.pdf}")
    private String folderPath;

    @PostConstruct
    private void init() {
        File file = new File(folderPath);
        if (!file.exists()) {
            file.mkdirs();
            System.out.println("folder created successfully");
        } else {
            System.out.println("folder already exists");
        }

    }

    @Override
    public List<PdfFileDto> findAllPdfFile() {
        return pdfFileMapper.toDtos(pdfFileRepo.findAll());
    }

    @Override
    public List<PdfFileDto> findAllPdfFileByPartId(int id) {
        return pdfFileMapper.toDtos(pdfFileRepo.findAllByPart_Id(id));
    }

    @Override
    public void addPdfFile(AddPdfFileDto dto) throws IOException {
        Part part = partRepo.findById(dto.getPartId()).orElse(null);

        if (part != null) {
            MultipartFile file = dto.getFile();
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();


            String cleanFileName = StringUtils.cleanPath(fileName);
            String cleanFolder = StringUtils.cleanPath(folderPath);

            Path path = Paths.get(cleanFolder, cleanFileName);

            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

//        String fileName = fileService.uploadFile(dto.getFile());

            PdfFile pdfFile = PdfFile.builder().id(UUID.randomUUID().toString()).filePath(path.toString()).title(dto.getTitle()).contentType(contentType).part(part).build();

            pdfFileRepo.save(pdfFile);
        }

    }

//    @Override
//    public void updatePdfFile(UpdatePdfFileDto dto, int id) {
//
//        PdfFile pdfFile = pdfFileRepo.findById(id).get();
//
//        pdfFile.setTitle(dto.getTitle());
//
//        pdfFileRepo.save(pdfFile);
//    }

    @Override
    public void deletePdfFileById(String id) {
        PdfFile pdfFile = pdfFileRepo.findById(id).orElse(null);
        if (pdfFile != null) {
            Path path = Paths.get(pdfFile.getFilePath());
            if (Files.exists(path)) {
                try {
                    Files.delete(path);
                    pdfFileRepo.delete(pdfFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public PdfFile findById(String id) {
        return pdfFileRepo.findById(id).orElse(null);
    }
}
