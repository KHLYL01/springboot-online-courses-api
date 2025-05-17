package com.example.alphaapi.service.impl;

import com.example.alphaapi.model.dto.*;
import com.example.alphaapi.model.entity.Part;
import com.example.alphaapi.model.entity.Quiz;
import com.example.alphaapi.model.mapper.QuizMapper;
import com.example.alphaapi.repo.PartRepo;
import com.example.alphaapi.repo.QuizRepo;
import com.example.alphaapi.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImp implements QuizService {

    private final PartRepo partRepo;

    private final QuizRepo quizRepo;

    private final QuizMapper quizMapper;

    @Override
    public List<QuizDto> findAllQuiz() {
        return quizMapper.toDtos(quizRepo.findAll());
    }

    @Override
    public List<QuizDto> findAllQuizByPartIdForUser(int id) {
        return quizMapper.toDtos(quizRepo.findAllByPart_IdAndValidTrue(id));
    }

    @Override
    public List<QuizDto> findAllQuizByPartId(int id) {
        return quizMapper.toDtos(quizRepo.findAllByPart_Id(id));
    }

    @Override
    public void addQuiz(AddQuizDto dto) {

        Part part = partRepo.findById(dto.getPartId()).get();

        Quiz quiz = quizMapper.toAddEntity(dto);
        quiz.setPart(part);
        quiz.setValid(false);

        quizRepo.save(quiz);
    }

    @Override
    public void deleteQuizById(int id) {
        quizRepo.deleteById(id);
    }

    @Override
    public void changeVisibility(int id) {
        Quiz quiz = quizRepo.findById(id).get();
        quiz.setValid(!quiz.isValid());
        quizRepo.save(quiz);
    }
}
