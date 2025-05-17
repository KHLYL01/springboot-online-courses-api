package com.example.alphaapi.service.impl;

import com.example.alphaapi.model.dto.AddQuizAttemptDto;
import com.example.alphaapi.model.dto.QuizAttemptDto;
import com.example.alphaapi.model.entity.Quiz;
import com.example.alphaapi.model.entity.QuizAttempt;
import com.example.alphaapi.model.entity.User;
import com.example.alphaapi.model.mapper.QuizAttemptMapper;
import com.example.alphaapi.repo.QuizAttemptRepo;
import com.example.alphaapi.repo.QuizRepo;
import com.example.alphaapi.repo.UserRepo;
import com.example.alphaapi.service.QuizAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizAttemptServiceImp implements QuizAttemptService {

    private final QuizRepo quizRepo;

    private final UserRepo userRepo;

    private final QuizAttemptRepo quizAttemptRepo;

    private final QuizAttemptMapper quizAttemptMapper;

    @Override
    public List<QuizAttemptDto> findAllQuizAttempt() {
        return quizAttemptMapper.toDtos(quizAttemptRepo.findAll());
    }

    @Override
    public List<QuizAttemptDto> findAllQuizAttemptByQuizId(int id) {
        return quizAttemptMapper.toDtos(quizAttemptRepo.findAllByQuiz_Id(id));
    }

    @Override
    public List<QuizAttemptDto> findAllQuizAttemptByUserId(int userId, int quizId) {
        return quizAttemptMapper.toDtos(quizAttemptRepo.findAllByUser_IdAndQuiz_Id(userId, quizId));
    }


    @Override
    public void addQuizAttempt(AddQuizAttemptDto dto) {

        Quiz quiz = quizRepo.findById(dto.getQuizId()).get();

        User user = userRepo.findById(dto.getUserId()).get();

        QuizAttempt quizAttempt = quizAttemptMapper.toAddEntity(dto);
        quizAttempt.setUser(user);
        quizAttempt.setQuiz(quiz);

        quizAttemptRepo.save(quizAttempt);
    }


    @Override
    public void deleteQuizAttemptById(int id) {
        quizAttemptRepo.deleteById(id);
    }
}
