package com.example.alphaapi.service.impl;

import com.example.alphaapi.model.dto.*;
import com.example.alphaapi.model.entity.CodeGroup;
import com.example.alphaapi.model.entity.Course;
import com.example.alphaapi.model.entity.Part;
import com.example.alphaapi.model.mapper.CodeGroupMapper;
import com.example.alphaapi.repo.CodeGroupRepo;
import com.example.alphaapi.repo.CodeRepo;
import com.example.alphaapi.repo.CourseRepo;
import com.example.alphaapi.service.CodeGroupService;
import com.example.alphaapi.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeGroupServiceImp implements CodeGroupService {

    private final CodeGroupRepo codeGroupRepo;

    private final CodeRepo codeRepo;

    private final CourseRepo courseRepo;

    private final CodeService codeService;

    private final CodeGroupMapper codeGroupMapper;



    @Override
    public List<CodeGroupDto> findAllCodeGroup() {
        return codeGroupMapper.toDtos(codeGroupRepo.findAll());
    }


    @Override
    public void addCodeGroup(AddCodeGroupDto dto) {
        for (String id : dto.getCourseIds().split(",")) {
            if(!courseRepo.existsById(Integer.parseInt(id))){
                throw new IllegalArgumentException("Invalid courses id");
            }
        }

        CodeGroup codeGroup = codeGroupRepo.save(codeGroupMapper.toAddEntity(dto));

        codeService.addAllCode(AddAllCodeDto.builder()
                .codeGroupId(codeGroup.getId())
                .number(dto.getCodeNumber())
                .build());
    }

    @Override
    public void updateCodeGroup(AddCodeGroupDto dto, int id) {

        CodeGroup codeGroup = codeGroupRepo.findById(id).get();

        codeGroup.setName(dto.getName());
        codeGroup.setCourseIds(dto.getCourseIds());

        codeGroupRepo.save(codeGroup);
    }

    @Override
    public void deleteCodeGroupById(int id) {
        codeRepo.deleteAll(codeRepo.findAllByCodeGroup_Id(id));
        codeGroupRepo.deleteById(id);
    }

}
