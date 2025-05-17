package com.example.alphaapi.controller;

import com.example.alphaapi.model.dto.AddAllQuizItemDto;
import com.example.alphaapi.model.dto.AddQuizItemDto;
import com.example.alphaapi.service.QuizItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/items")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class QuizItemController {

    private final QuizItemService quizItemService;

    @GetMapping
    public ResponseEntity<?> findAllQuizItem() {
        return ResponseEntity.ok(quizItemService.findAllQuizItem());
    }


    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("quizzes/{id}")
    public ResponseEntity<?> findAllQuizItemByQuizId(@PathVariable int id) {
        return ResponseEntity.ok(quizItemService.findAllQuizItemByQuizId(id));
    }

    @PostMapping
    public ResponseEntity<?> addQuizItem(@RequestBody AddQuizItemDto addQuizItemDto) {
        quizItemService.addQuizItem(addQuizItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/all")
    public ResponseEntity<?> addAllQuizItem(@RequestBody AddAllQuizItemDto addQuizItemDto) {
        quizItemService.addAllQuizItem(addQuizItemDto.getQuizItems());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuizItemById(@PathVariable int id) {
        quizItemService.deleteQuizItemById(id);
        return ResponseEntity.noContent().build();
    }

}
