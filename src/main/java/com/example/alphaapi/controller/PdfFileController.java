package com.example.alphaapi.controller;

import com.example.alphaapi.model.dto.AddPdfFileDto;
import com.example.alphaapi.model.entity.PdfFile;
import com.example.alphaapi.service.PdfFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class PdfFileController {

    private final PdfFileService pdfFileService;

    @GetMapping
    public ResponseEntity<?> findAllPdfFile() {
        return ResponseEntity.ok(pdfFileService.findAllPdfFile());
    }


    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("parts/{id}")
    public ResponseEntity<?> findAllPdfFileByPartId(@PathVariable int id) {
        return ResponseEntity.ok(pdfFileService.findAllPdfFileByPartId(id));
    }


    //    @PostMapping(consumes = "multipart/form-data")
//    public ResponseEntity<?> addPdfFile(@RequestPart("file") MultipartFile file,
//                                        @ModelAttribute AddPdfFileDto addPdfFileDto) throws IOException {
//        pdfFileService.addPdfFile(file, addPdfFileDto);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> addPdfFile(@ModelAttribute AddPdfFileDto addPdfFileDto) throws IOException {
        pdfFileService.addPdfFile(addPdfFileDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updatePdfFile(@RequestBody UpdatePdfFileDto dto, @PathVariable int id) {
//        pdfFileService.updatePdfFile(dto, id);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePdfFileById(@PathVariable String id) {
        pdfFileService.deletePdfFileById(id);
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewFile(@PathVariable String id) throws IOException {

        PdfFile pdfFile = pdfFileService.findById(id);

        File file = new File(pdfFile.getFilePath());

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=myDoc.pdf");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path filePath = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(filePath));


        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

}
