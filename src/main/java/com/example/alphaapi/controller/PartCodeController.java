package com.example.alphaapi.controller;

import com.example.alphaapi.model.dto.AddAllPartCodeDto;
import com.example.alphaapi.model.dto.AddPartCodeDto;
import com.example.alphaapi.service.PartCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/part-codes")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class PartCodeController {

    private final PartCodeService partCodeService;

    @GetMapping
    public ResponseEntity<?> findAllCode() {
        return ResponseEntity.ok(partCodeService.findAllPartCode());
    }

    @GetMapping("/parts/{id}")
    public ResponseEntity<?> findAllCodeByPartId(@PathVariable int id) {
        return ResponseEntity.ok(partCodeService.findAllPartCodeByPartId(id));
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<?> findAllCodeByMacDeviceCode(@PathVariable int id, @RequestParam(name = "code") String code) {
        return ResponseEntity.ok(partCodeService.findAllByMacDeviceCode(id, code));
    }

    @PostMapping
    public ResponseEntity<?> addCode(@RequestBody AddPartCodeDto dto) {
        partCodeService.addPartCode(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/all")
    public ResponseEntity<?> addAllCode(@RequestBody AddAllPartCodeDto dto) {
        partCodeService.addAllPartCode(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


//    // no usage
//    @PutMapping("/{dateTime}")
//    public ResponseEntity<?> disableAllCodeByDateTime(
//            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//                    LocalDateTime dateTime) {
//        partCodeService.disableAllPartCodeByDateTime(dateTime);
//        return ResponseEntity.noContent().build();
//    }


    @DeleteMapping
    public ResponseEntity<?> deleteAllCodeByIds(@RequestParam("ids") List<Integer> ids) {
        partCodeService.deleteAllByIds(ids);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCodeById(@PathVariable int id) {
        partCodeService.deletePartCodeById(id);
        return ResponseEntity.noContent().build();
    }

}
