package com.example.alphaapi.service.impl;

import com.example.alphaapi.model.dto.AddLessonDto;
import com.example.alphaapi.model.dto.LessonDto;
import com.example.alphaapi.model.dto.UpdateLessonDto;
import com.example.alphaapi.model.entity.Lesson;
import com.example.alphaapi.model.entity.Part;
import com.example.alphaapi.model.mapper.LessonMapper;
import com.example.alphaapi.repo.LessonRepo;
import com.example.alphaapi.repo.PartRepo;
import com.example.alphaapi.service.LessonService;
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
public class LessonServiceImp implements LessonService {

    private final PartRepo partRepo;

    private final LessonRepo lessonRepo;

    private final LessonMapper lessonMapper;

    @Value("${file.path.video}")
    private String folderPath;

    @PostConstruct
    public void init() {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
            System.out.println("Folder created successfully");
        } else {
            System.out.println("Folder already exists");
        }
    }

    @Override
    public List<LessonDto> findAllLesson() {
        return lessonMapper.toDtos(lessonRepo.findAll());
    }

    @Override
    public List<LessonDto> findAllLessonByPartId(int id) {
        return lessonMapper.toDtos(lessonRepo.findAllByPartIdOrderByCreateAtAsc(id));
    }

    @Override
    public void addLesson(AddLessonDto dto) throws IOException {
        Part part = partRepo.findById(dto.getPartId()).orElse(null);
        if (part != null) {
            MultipartFile video = dto.getFile();
            String filename = video.getOriginalFilename();
            String contentType = video.getContentType();
            InputStream inputStream = video.getInputStream();

            String clearFilename = StringUtils.cleanPath(filename);
            String clearFolder = StringUtils.cleanPath(folderPath);

            Path path = Paths.get(clearFolder, clearFilename);

            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            Lesson lesson = Lesson.builder()
                    .id(UUID.randomUUID().toString())
                    .title(dto.getTitle())
                    .part(part)
                    .contentType(contentType)
                    .filePath(path.toString())
                    .build();
            lessonRepo.save(lesson);
        }
    }

//    @Override
//    public void updateLesson(UpdateLessonDto dto, int id) {
//
//        Lesson lesson = lessonRepo.findById(id).get();
//
//        lesson.setTitle(dto.getTitle());
//        lesson.setVideoUrl(dto.getVideoUrl());
//
//        lessonRepo.save(lesson);
//    }

    @Override
    public void deleteLessonById(String id) {
        Lesson lesson = lessonRepo.findById(id).orElse(null);
        if (lesson != null) {
            Path path = Paths.get(lesson.getFilePath());
            if (Files.exists(path)) {
                try {
                    Files.delete(path);
                    lessonRepo.delete(lesson);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Lesson getById(String videoId) {
        return lessonRepo.findById(videoId).orElseThrow();
    }
}
