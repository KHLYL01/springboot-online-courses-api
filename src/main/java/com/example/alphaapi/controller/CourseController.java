package com.example.alphaapi.controller;

import com.example.alphaapi.model.dto.AddCourseDto;
import com.example.alphaapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/courses")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class CourseController {

    private final CourseService courseService;

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/valid")
    public ResponseEntity<?> findAllCourseForUser() {
        return ResponseEntity.ok(courseService.findAllCourseForUser());
    }


    @GetMapping
    public ResponseEntity<?> findAllCourse() {
        return ResponseEntity.ok(courseService.findAllCourse());
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/{id}/contents")
    public ResponseEntity<?> findAllCourseContentsById(@PathVariable int id) {
        return ResponseEntity.ok(courseService.findAllCourseContentsById(id));
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody AddCourseDto dto) {
        courseService.addCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@RequestBody AddCourseDto dto, @PathVariable int id) {
        courseService.updateCourse(dto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable int id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/valid")
    public ResponseEntity<?> changeVisibility(@PathVariable int id) {
        courseService.changeVisibility(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/mode")
    public ResponseEntity<?> changeMode(@PathVariable int id) {
        courseService.changeMode(id);
        return ResponseEntity.noContent().build();
    }

}
