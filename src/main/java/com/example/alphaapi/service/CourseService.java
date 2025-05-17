package com.example.alphaapi.service;

import com.example.alphaapi.model.dto.AddCourseDto;
import com.example.alphaapi.model.dto.CourseDto;
import com.example.alphaapi.model.dto.ContentDto;
import com.example.alphaapi.model.entity.Course;

import java.util.List;


public interface CourseService {
    
    List<CourseDto> findAllCourseForUser();

    List<CourseDto> findAllCourse();

    List<ContentDto> findAllCourseContentsById(int id);

    void addCourse(AddCourseDto dto);

    void updateCourse(AddCourseDto dto, int id);

    void deleteCourseById(int id);

    void changeVisibility(int id);

    void changeMode(int id);

}
