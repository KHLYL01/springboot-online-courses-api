package com.example.alphaapi.repo;

import com.example.alphaapi.model.entity.QuizItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizItemRepo extends JpaRepository<QuizItem, Integer> {

    List<QuizItem> findAllByQuiz_Id(int id);

}
