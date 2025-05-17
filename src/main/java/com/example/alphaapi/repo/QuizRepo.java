package com.example.alphaapi.repo;

import com.example.alphaapi.model.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {

    List<Quiz> findAllByPart_Id(int id);

    List<Quiz> findAllByPart_IdAndValidTrue(int id);
}
