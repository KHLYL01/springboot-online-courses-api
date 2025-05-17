package com.example.alphaapi.service.impl;

import com.example.alphaapi.model.dto.*;
import com.example.alphaapi.model.entity.*;
import com.example.alphaapi.model.enums.CodeStatus;
import com.example.alphaapi.model.mapper.CodeMapper;
import com.example.alphaapi.repo.*;
import com.example.alphaapi.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeServiceImp implements CodeService {

    private final CodeRepo codeRepo;
    private final PartCodeRepo partCodeRepo;

    private final CodeGroupRepo codeGroupRepo;

    private final UserRepo userRepo;

    private final CodeMapper codeMapper;

    @Override
    public List<CodeDto> findAllCode() {
        return codeMapper.toDtos(codeRepo.findAll());
    }

    @Override
    public List<CodeDto> findAllCodeByGroupId(int id) {
        return codeMapper.toDtos(codeRepo.findAllByCodeGroup_Id(id));
    }

    @Override
    public List<CodeDto> findAllByMacDeviceCode(int id, String deviceCode) {
        return codeMapper.toDtos(codeRepo.findAllByUser_IdAndMacDeviceCodeAndCodeStatus(id, deviceCode, CodeStatus.SOLD));
    }

    @Override
    public void addAllCode(AddAllCodeDto dto) {
        for (int i = 0; i < dto.getNumber(); i++) {
            addCode(AddCodeDto.builder()
                    .codeGroupId(dto.getCodeGroupId())
                    .build());
        }
    }


    @Override
    public void addCode(AddCodeDto dto) {
        String symbol;
        do {
            symbol = RandomStringUtils.random(8, "0123456789ABCDEFGHIJKLMNOPQRSTVUWXYZ");
        } while (codeRepo.existsBySymbol(symbol) ||
                partCodeRepo.existsBySymbol(symbol));

        CodeGroup codeGroup = codeGroupRepo.findById(dto.getCodeGroupId()).get();

        Code code = Code.builder()
                .symbol(symbol)
                .codeGroup(codeGroup)
                .codeStatus(CodeStatus.AVAILABLE)
                .build();
        codeRepo.save(code);
    }


    @Override
    public void activeCode(ActiveCodeDto dto) {
        Code code = codeRepo.findBySymbolEquals(dto.getSymbol())
                .orElseThrow(
                        () -> new IllegalArgumentException("this code is not found")
                );

        if (code.getCodeStatus() != CodeStatus.AVAILABLE) {
            throw new IllegalArgumentException("this code is invalid");
        }

        CodeGroup group = code.getCodeGroup();
        group.setNumberOfStudent(group.getNumberOfStudent()+1);
        codeGroupRepo.save(group);

        User user = userRepo.findById(dto.getUserId()).get();

        code.setUser(user);
        code.setMacDeviceCode(dto.getMacDeviceCode());
        code.setCodeStatus(CodeStatus.SOLD);
        code.setEnableDate(LocalDateTime.now());

        codeRepo.save(code);
    }


//    @Override
//    public void disableAllCodeByDateTime(LocalDateTime dateTime) {
//        List<Code> codeList = codeRepo.findAllByEnableDateBefore(dateTime);
//        codeList.forEach(partCode -> partCode.setCodeStatus(CodeStatus.EXPIRED));
//        codeRepo.saveAll(codeList);
//    }

    @Override
    public void deleteAllByIds(List<Integer> ids) {
        codeRepo.deleteAllById(ids);
    }

    @Override
    public void deleteCodeById(int id) {
        codeRepo.deleteById(id);
    }
}
