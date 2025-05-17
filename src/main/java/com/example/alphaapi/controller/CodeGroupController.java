package com.example.alphaapi.controller;

import com.example.alphaapi.model.dto.AddCodeGroupDto;
import com.example.alphaapi.model.dto.AddCourseDto;
import com.example.alphaapi.service.CodeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/groups")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class CodeGroupController {

    private final CodeGroupService codeGroupService;


    @GetMapping
    public ResponseEntity<?> findAllCodeGroup() {
        return ResponseEntity.ok(codeGroupService.findAllCodeGroup());
    }

    @PostMapping
    public ResponseEntity<?> addCodeGroup(@RequestBody AddCodeGroupDto dto) {
        codeGroupService.addCodeGroup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCodeGroup(@RequestBody AddCodeGroupDto dto, @PathVariable int id) {
        codeGroupService.updateCodeGroup(dto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCodeGroupById(@PathVariable int id) {
        codeGroupService.deleteCodeGroupById(id);
        return ResponseEntity.noContent().build();
    }

}
