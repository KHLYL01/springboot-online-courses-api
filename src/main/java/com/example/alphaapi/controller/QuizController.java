package com.example.alphaapi.controller;

import com.example.alphaapi.model.dto.AddQuizDto;
import com.example.alphaapi.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/quizzes")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public ResponseEntity<?> findAllQuiz() {
        return ResponseEntity.ok(quizService.findAllQuiz());
    }


    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("parts/{id}/valid")
    public ResponseEntity<?> findAllQuizByPartIdForUser(@PathVariable int id) {
        return ResponseEntity.ok(quizService.findAllQuizByPartIdForUser(id));
    }

    @GetMapping("parts/{id}")
    public ResponseEntity<?> findAllQuizByPartId(@PathVariable int id) {
        return ResponseEntity.ok(quizService.findAllQuizByPartId(id));
    }

    @PostMapping
    public ResponseEntity<?> addQuiz(@RequestBody AddQuizDto addQuizDto) {
        quizService.addQuiz(addQuizDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuizById(@PathVariable int id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/valid")
    public ResponseEntity<?> changeVisibility(@PathVariable int id) {
        quizService.changeVisibility(id);
        return ResponseEntity.noContent().build();
    }

}
