package com.example.alphaapi.controller;

import com.example.alphaapi.model.dto.*;
import com.example.alphaapi.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/codes")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class CodeController {

    private final CodeService codeService;

    @GetMapping
    public ResponseEntity<?> findAllCode() {
        return ResponseEntity.ok(codeService.findAllCode());
    }

  @GetMapping("/groups/{id}")
    public ResponseEntity<?> findAllCodeByGroupId(@PathVariable int id) {
        return ResponseEntity.ok(codeService.findAllCodeByGroupId(id));
    }


    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<?> findAllCodeByMacDeviceCode(@PathVariable int id, @RequestParam(name = "code") String code) {
        return ResponseEntity.ok(codeService.findAllByMacDeviceCode(id, code));
    }

    @PostMapping
    public ResponseEntity<?> addCode(@RequestBody AddCodeDto dto) {
        codeService.addCode(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/all")
    public ResponseEntity<?> addAllCode(@RequestBody AddAllCodeDto dto) {
        codeService.addAllCode(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

//    // no usage
//    @PutMapping("/{dateTime}")
//    public ResponseEntity<?> disableAllCodeByDateTime(
//            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//                    LocalDateTime dateTime) {
//        codeService.disableAllCodeByDateTime(dateTime);
//        return ResponseEntity.noContent().build();
//    }


    @DeleteMapping
    public ResponseEntity<?> deleteAllCodeByIds(@RequestParam("ids") List<Integer> ids) {
        codeService.deleteAllByIds(ids);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCodeById(@PathVariable int id) {
        codeService.deleteCodeById(id);
        return ResponseEntity.noContent().build();
    }

}
