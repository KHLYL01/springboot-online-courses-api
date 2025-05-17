package com.example.alphaapi.controller;

import com.example.alphaapi.model.dto.*;
import com.example.alphaapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    final private UserService userService;

    @GetMapping("/count")
    public ResponseEntity<?> getNumberOfUser() {
        return ResponseEntity.ok(Map.of("numberOfUsers",userService.countAllUser()));
    }

}
