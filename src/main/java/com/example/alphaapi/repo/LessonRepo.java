package com.example.alphaapi.repo;

import com.example.alphaapi.model.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepo extends JpaRepository<Lesson, String> {

    List<Lesson> findAllByPart_Id(int id);

}
