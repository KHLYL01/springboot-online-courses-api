package com.example.alphaapi.service;

import com.example.alphaapi.model.dto.*;

import java.util.List;


public interface CodeGroupService {

    List<CodeGroupDto> findAllCodeGroup();

    void addCodeGroup(AddCodeGroupDto dto);

    void updateCodeGroup(AddCodeGroupDto dto, int id);

    void deleteCodeGroupById(int id);

}
