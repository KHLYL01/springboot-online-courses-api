package com.example.alphaapi.controller;

import com.example.alphaapi.model.dto.ActiveCodeDto;
import com.example.alphaapi.service.CodeService;
import com.example.alphaapi.service.PartCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/actives")
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
@RequiredArgsConstructor
public class ActiveCodeController {

    private final CodeService codeService;
    private final PartCodeService partCodeService;

    @PutMapping
    public ResponseEntity<?> activeCode(@RequestBody ActiveCodeDto dto) {
        if (dto.isCourse()) {
            codeService.activeCode(dto);
        } else {
            partCodeService.activePartCode(dto);
        }
        return ResponseEntity.noContent().build();
    }

}
