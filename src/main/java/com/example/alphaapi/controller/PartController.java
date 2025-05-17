package com.example.alphaapi.controller;

import com.example.alphaapi.model.dto.AddPartDto;
import com.example.alphaapi.model.dto.UpdatePartDto;
import com.example.alphaapi.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/parts")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class PartController {

    private final PartService partService;


    @GetMapping
    public ResponseEntity<?> findAllPart() {
        return ResponseEntity.ok(partService.findAllPart());
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/courses/{id}/valid")
    public ResponseEntity<?> findAllPartByCourseIdForUser(@PathVariable int id) {
        return ResponseEntity.ok(partService.findAllPartByCourseIdForUser(id));
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<?> findAllPartByCourseId(@PathVariable int id) {
        return ResponseEntity.ok(partService.findAllPartByCourseId(id));
    }

//    @GetMapping("{id}/contents")
//    public ResponseEntity<?> findAllContentByPartId(@PathVariable int id) {
//        return ResponseEntity.ok(partService.findAllContentByPartId(id));
//    }

    @PostMapping
    public ResponseEntity<?> addPart(@RequestBody AddPartDto dto) {
        partService.addPart(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePart(@RequestBody UpdatePartDto dto, @PathVariable int id) {
        partService.updatePart(dto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePartById(@PathVariable int id) {
        partService.deletePartById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/valid")
    public ResponseEntity<?> changeVisibility(@PathVariable int id) {
        partService.changeVisibility(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/mode")
    public ResponseEntity<?> changeMode(@PathVariable int id) {
        partService.changeMode(id);
        return ResponseEntity.noContent().build();
    }
}
