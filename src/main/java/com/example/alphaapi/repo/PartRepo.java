package com.example.alphaapi.repo;

import com.example.alphaapi.model.entity.Course;
import com.example.alphaapi.model.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepo extends JpaRepository<Part, Integer> {

    List<Part> findAllByCourse_Id(int id);

    List<Part> findAllByCourse_IdAndValidTrue(int id);

    @Query("select p.course from Part p where p.id = :id")
    Course findCourseByPartId(int id);
}
