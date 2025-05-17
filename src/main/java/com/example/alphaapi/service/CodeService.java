package com.example.alphaapi.service;

import com.example.alphaapi.model.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface CodeService {

    List<CodeDto> findAllCode();

    List<CodeDto> findAllCodeByGroupId(int id);

    List<CodeDto> findAllByMacDeviceCode(int id, String deviceCode);

    @Transactional
    void addAllCode(AddAllCodeDto dto);

    void addCode(AddCodeDto dto);

    @Transactional
    void activeCode(ActiveCodeDto dto);

//    void disableAllCodeByDateTime(LocalDateTime dateTime);

    void deleteAllByIds(List<Integer> ids);

    void deleteCodeById(int id);
}
