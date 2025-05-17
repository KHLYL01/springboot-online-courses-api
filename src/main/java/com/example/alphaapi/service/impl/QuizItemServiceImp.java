package com.example.alphaapi.service.impl;

import com.example.alphaapi.model.dto.AddQuizItemDto;
import com.example.alphaapi.model.dto.QuizItemDto;
import com.example.alphaapi.model.entity.Quiz;
import com.example.alphaapi.model.entity.QuizItem;
import com.example.alphaapi.model.mapper.QuizItemMapper;
import com.example.alphaapi.repo.QuizItemRepo;
import com.example.alphaapi.repo.QuizRepo;
import com.example.alphaapi.service.QuizItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizItemServiceImp implements QuizItemService {


    private final QuizRepo quizRepo;

    private final QuizItemRepo quizItemRepo;

    private final QuizItemMapper quizItemMapper;

    @Override
    public List<QuizItemDto> findAllQuizItem() {
        return quizItemMapper.toDtos(quizItemRepo.findAll());
    }

    @Override
    public List<QuizItemDto> findAllQuizItemByQuizId(int id) {
        return quizItemMapper.toDtos(quizItemRepo.findAllByQuiz_Id(id));
    }

    @Override
    public void addQuizItem(AddQuizItemDto dto) {

        Quiz quiz = quizRepo.findById(dto.getQuizId()).get();

        QuizItem quizItem = quizItemMapper.toAddEntity(dto);
        quizItem.setQuiz(quiz);

        quizItemRepo.save(quizItem);
    }

    @Override
    public void addAllQuizItem(List<AddQuizItemDto> dtos) {

        Quiz quiz = quizRepo.findById(dtos.get(0).getQuizId()).get();

        List<QuizItem> quizItemList = dtos.stream().map(quizItemMapper::toAddEntity).toList();
        quizItemList.forEach(quizItem -> quizItem.setQuiz(quiz));

        quizItemRepo.saveAll(quizItemList);

    }

    @Override
    public void deleteQuizItemById(int id) {
        quizItemRepo.deleteById(id);
    }
}
