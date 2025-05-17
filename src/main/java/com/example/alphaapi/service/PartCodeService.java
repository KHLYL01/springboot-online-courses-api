package com.example.alphaapi.service;

import com.example.alphaapi.model.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


public interface PartCodeService {

    List<PartCodeDto> findAllPartCode();

    List<PartCodeDto> findAllPartCodeByPartId(int id);

    List<PartCodeDto> findAllByMacDeviceCode(int id, String deviceCode);

    @Transactional
    void addAllPartCode(AddAllPartCodeDto dto);

    void addPartCode(AddPartCodeDto dto);

    void activePartCode(ActiveCodeDto dto);

//    void disableAllPartCodeByDateTime(LocalDateTime dateTime);

    void deleteAllByIds(List<Integer> ids);

    void deletePartCodeById(int id);
}
