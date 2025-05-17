package com.example.alphaapi.service;

import com.example.alphaapi.model.dto.AddLessonDto;
import com.example.alphaapi.model.dto.LessonDto;
import com.example.alphaapi.model.dto.UpdateLessonDto;
import com.example.alphaapi.model.entity.Lesson;

import java.io.IOException;
import java.util.List;


public interface LessonService {

    List<LessonDto> findAllLesson();

    List<LessonDto> findAllLessonByPartId(int id);

    void addLesson(AddLessonDto dto) throws IOException;

//    void updateLesson(UpdateLessonDto dto, int id);

    void deleteLessonById(String id);

    Lesson getById(String videoId);
}
