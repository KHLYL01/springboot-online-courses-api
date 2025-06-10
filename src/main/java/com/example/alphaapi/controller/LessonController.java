package com.example.alphaapi.controller;

import com.example.alphaapi.AppConstants;
import com.example.alphaapi.model.dto.AddLessonDto;
import com.example.alphaapi.model.dto.UpdateLessonDto;
import com.example.alphaapi.model.entity.Lesson;
import com.example.alphaapi.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/v1/lessons")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<?> findAllLesson() {
        return ResponseEntity.ok(lessonService.findAllLesson());
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("parts/{id}")
    public ResponseEntity<?> findAllLessonByPartId(@PathVariable int id) {
        return ResponseEntity.ok(lessonService.findAllLessonByPartId(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addLesson(@ModelAttribute AddLessonDto dto) throws IOException {
        lessonService.addLesson(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateLesson(@RequestBody UpdateLessonDto dto, @PathVariable int id) {
//        lessonService.updateLesson(dto, id);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLessonById(@PathVariable String id) {
        lessonService.deleteLessonById(id);
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/stream/{videoId}")
    public ResponseEntity<Resource> stream(@PathVariable String videoId) {

        Lesson video = lessonService.getById(videoId);

        String contentType = video.getContentType();
        String filePath = video.getFilePath();

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        Resource resource = new FileSystemResource(filePath);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/stream/range/{vedioId}")
    public ResponseEntity<Resource> streamInRange(@PathVariable String vedioId, @RequestHeader(value = "Range", required = false) String range) {

//        System.out.println("range: "+range);

        Lesson lesson = lessonService.getById(vedioId);
        Path path = Paths.get(lesson.getFilePath());

        Resource resource = new FileSystemResource(path);

        String contentType = lesson.getContentType();

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        long fileLength = path.toFile().length();

        if (range == null) {
            System.out.println("hi");
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        }

        long rangeStart, rangeEnd;

        String[] rangeSplit = range.replace("bytes=", "").split("-");
        rangeStart = Long.parseLong(rangeSplit[0]);
        rangeEnd = rangeStart + AppConstants.CHUNK_SIZE - 1;

        if (rangeEnd > fileLength) {
            rangeEnd = fileLength - 1;
        }

//        System.out.println("range start: " + rangeStart);
//        System.out.println("range end: " + rangeEnd);

        InputStream inputStream;

        try {
            inputStream = Files.newInputStream(path);
            inputStream.skip(rangeStart);
            long contentLength = rangeEnd - rangeStart + 1;

            byte[] buffer = new byte[(int) contentLength];
            int bytesRead = inputStream.read(buffer, 0, buffer.length);
//            System.out.println("bytesRead: " + bytesRead);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + fileLength);
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("X-Content-Type-Options", "nosniff");
            headers.setContentLength(contentLength);

            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .headers(headers)
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(new ByteArrayResource(buffer));

        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
