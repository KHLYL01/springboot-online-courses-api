package com.example.alphaapi.model.mapper;

import com.example.alphaapi.model.dto.AddCourseDto;
import com.example.alphaapi.model.dto.CourseDto;
import com.example.alphaapi.model.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseMapper {

    public List<CourseDto> toDtos(List<Course> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public CourseDto toDto(Course entity) {
        return CourseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .teacherName(entity.getTeacherName())
                .price(entity.getPrice())
                .valid(entity.isValid())
                .free(entity.isFree())
                .build();
    }

    public Course toEntity(CourseDto dto) {
        return Course.builder()
                .id(dto.getId())
                .name(dto.getName())
                .teacherName(dto.getTeacherName())
                .price(dto.getPrice())
                .valid(dto.isValid())
                .free(dto.isFree())
                .build();
    }

    public Course toAddEntity(AddCourseDto dto){
        return Course.builder()
                .name(dto.getName())
                .teacherName(dto.getTeacherName())
                .price(dto.getPrice())
                .build();
    }
}
