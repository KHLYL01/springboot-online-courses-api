package com.example.alphaapi.controller;

import com.example.alphaapi.model.dto.AddQuizAttemptDto;
import com.example.alphaapi.service.QuizAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/attempts")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class QuizAttemptController {

    private final QuizAttemptService quizAttemptService;

    @GetMapping
    public ResponseEntity<?> findAllQuizAttempt() {
        return ResponseEntity.ok(quizAttemptService.findAllQuizAttempt());
    }

    @GetMapping("quizzes/{id}")
    public ResponseEntity<?> findAllQuizAttemptByQuizId(@PathVariable int id) {
        return ResponseEntity.ok(quizAttemptService.findAllQuizAttemptByQuizId(id));
    }


    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("users/{userId}/quizzes/{quizId}")
    public ResponseEntity<?> findAllQuizAttemptByUserId(@PathVariable int userId, @PathVariable int quizId) {
        return ResponseEntity.ok(quizAttemptService.findAllQuizAttemptByUserId(userId, quizId));
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @PostMapping
    public ResponseEntity<?> addQuizAttempt(@RequestBody AddQuizAttemptDto addQuizAttemptDto) {
        quizAttemptService.addQuizAttempt(addQuizAttemptDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuizAttemptById(@PathVariable int id) {
        quizAttemptService.deleteQuizAttemptById(id);
        return ResponseEntity.noContent().build();
    }
}
