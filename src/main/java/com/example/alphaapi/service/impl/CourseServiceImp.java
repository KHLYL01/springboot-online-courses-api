package com.example.alphaapi.service.impl;

import com.example.alphaapi.model.dto.AddCourseDto;
import com.example.alphaapi.model.dto.ContentDto;
import com.example.alphaapi.model.dto.CourseDto;
import com.example.alphaapi.model.entity.Course;
import com.example.alphaapi.model.entity.Part;
import com.example.alphaapi.model.mapper.CourseMapper;
import com.example.alphaapi.repo.CourseRepo;
import com.example.alphaapi.repo.PartRepo;
import com.example.alphaapi.service.CourseService;
import com.example.alphaapi.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CourseServiceImp implements CourseService {

    private final CourseRepo courseRepo;

    private final PartRepo partRepo;

    private final CourseMapper courseMapper;

    private final PartService partService;

    @Override
    public List<CourseDto> findAllCourseForUser() {
        return courseMapper.toDtos(courseRepo.findAllByValidTrue());
    }

    @Override
    public List<CourseDto> findAllCourse() {
        return courseMapper.toDtos(courseRepo.findAll());
    }

    @Override
    public List<ContentDto> findAllCourseContentsById(int id) {
        List<Integer> partIds = partRepo.findAllByCourse_Id(id).stream().map(Part::getId).toList();
        return partIds.stream().map(partService::findAllContentByPartId).toList();
    }

    @Override
    public void addCourse(AddCourseDto dto) {
        Course course = courseMapper.toAddEntity(dto);
        course.setValid(false);
        course.setFree(false);
        courseRepo.save(course);
    }

    @Override
    public void updateCourse(AddCourseDto dto, int id) {

        Course course = courseRepo.findById(id).get();

        course.setName(dto.getName());
        course.setTeacherName(dto.getTeacherName());

        courseRepo.save(course);
    }

    @Override
    public void deleteCourseById(int id) {
        courseRepo.deleteById(id);
    }

    @Override
    public void changeVisibility(int id) {
        Course course = courseRepo.findById(id).get();
        course.setValid(!course.isValid());
        courseRepo.save(course);
    }

    @Override
    public void changeMode(int id) {
        Course course = courseRepo.findById(id).get();
        course.setFree(!course.isFree());
        for(Part part : partRepo.findAllByCourse_Id(id)){
            part.setFree(course.isFree());
            partRepo.save(part);
        }
        courseRepo.save(course);
    }

}
