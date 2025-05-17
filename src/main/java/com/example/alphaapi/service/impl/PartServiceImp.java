package com.example.alphaapi.service.impl;

import com.example.alphaapi.model.dto.AddPartDto;
import com.example.alphaapi.model.dto.ContentDto;
import com.example.alphaapi.model.dto.PartDto;
import com.example.alphaapi.model.dto.UpdatePartDto;
import com.example.alphaapi.model.entity.Course;
import com.example.alphaapi.model.entity.Part;
import com.example.alphaapi.model.mapper.PartMapper;
import com.example.alphaapi.repo.CourseRepo;
import com.example.alphaapi.repo.PartRepo;
import com.example.alphaapi.service.LessonService;
import com.example.alphaapi.service.PartService;
import com.example.alphaapi.service.PdfFileService;
import com.example.alphaapi.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartServiceImp implements PartService {

    private final PartRepo partRepo;

    private final CourseRepo courseRepo;

    private final PartMapper partMapper;

    private final LessonService lessonService;

    private final PdfFileService pdfFileService;

    private final QuizService quizService;


    @Override
    public List<PartDto> findAllPart() {
        return partMapper.toDtos(partRepo.findAll());
    }

    @Override
    public List<PartDto> findAllPartByCourseIdForUser(int id) {
        return partMapper.toDtos(partRepo.findAllByCourse_IdAndValidTrue(id));
    }

    @Override
    public List<PartDto> findAllPartByCourseId(int id) {
        return partMapper.toDtos(partRepo.findAllByCourse_Id(id));
    }

    @Override
    public ContentDto findAllContentByPartId(int id) {
        return ContentDto.builder()
                .lessons(lessonService.findAllLessonByPartId(id))
                .files(pdfFileService.findAllPdfFileByPartId(id))
                .quizzes(quizService.findAllQuizByPartId(id))
                .build();
    }

    @Override
    public void addPart(AddPartDto dto) {

        Course course = courseRepo.findById(dto.getCourseId()).get();

        Part part = partMapper.toAddEntity(dto);
        part.setCourse(course);
        part.setValid(false);
        part.setFree(false);

        partRepo.save(part);
    }

    @Override
    public void updatePart(UpdatePartDto dto, int id) {

        Part part = partRepo.findById(id).get();

        part.setName(dto.getName());
        part.setDescription(dto.getDescription());

        partRepo.save(part);
    }

    @Override
    public void deletePartById(int id) {
        partRepo.deleteById(id);
    }

    @Override
    public void changeVisibility(int id) {
        Part part = partRepo.findById(id).get();

        part.setValid(!part.isValid());

        partRepo.save(part);
    }

    @Override
    public void changeMode(int id) {
        Part part = partRepo.findById(id).get();
        part.setFree(!part.isFree());

        if(!part.isFree()) {
            Course course = partRepo.findCourseByPartId(id);
            System.out.println("hi : "+course.getName());
            course.setFree(false);
            courseRepo.save(course);
        }

        partRepo.save(part);



    }
}
