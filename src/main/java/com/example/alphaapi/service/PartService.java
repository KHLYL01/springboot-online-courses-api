package com.example.alphaapi.service;

import com.example.alphaapi.model.dto.AddPartDto;
import com.example.alphaapi.model.dto.ContentDto;
import com.example.alphaapi.model.dto.PartDto;
import com.example.alphaapi.model.dto.UpdatePartDto;

import java.util.List;


public interface PartService {

    List<PartDto> findAllPart();

    List<PartDto> findAllPartByCourseIdForUser(int id);

    List<PartDto> findAllPartByCourseId(int id);

    ContentDto findAllContentByPartId(int id);

    void addPart(AddPartDto dto);

    void updatePart(UpdatePartDto dto, int id);

    void deletePartById(int id);

    void changeVisibility(int id);

    void changeMode(int id);
}
