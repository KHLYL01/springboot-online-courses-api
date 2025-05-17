package com.example.alphaapi.repo;

import com.example.alphaapi.model.entity.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizAttemptRepo extends JpaRepository<QuizAttempt, Integer> {

    List<QuizAttempt> findAllByQuiz_Id(int id);

    List<QuizAttempt> findAllByUser_IdAndQuiz_Id(int userId,int quizId);
}
