package com.example.alphaapi.service.impl;

import com.example.alphaapi.model.dto.ActiveCodeDto;
import com.example.alphaapi.model.dto.AddAllPartCodeDto;
import com.example.alphaapi.model.dto.AddPartCodeDto;
import com.example.alphaapi.model.dto.PartCodeDto;
import com.example.alphaapi.model.entity.PartCode;
import com.example.alphaapi.model.entity.Part;
import com.example.alphaapi.model.entity.User;
import com.example.alphaapi.model.enums.CodeStatus;
import com.example.alphaapi.model.mapper.PartCodeMapper;
import com.example.alphaapi.repo.CodeRepo;
import com.example.alphaapi.repo.PartCodeRepo;
import com.example.alphaapi.repo.PartRepo;
import com.example.alphaapi.repo.UserRepo;
import com.example.alphaapi.service.PartCodeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartCodeServiceImp implements PartCodeService {

    private final PartCodeRepo partCodeRepo;

    private final CodeRepo codeRepo;

    private final PartRepo partRepo;

    private final UserRepo userRepo;

    private final PartCodeMapper partCodeMapper;

    @Override
    public List<PartCodeDto> findAllPartCode() {
        return partCodeMapper.toDtos(partCodeRepo.findAll());
    }

    @Override
    public List<PartCodeDto> findAllPartCodeByPartId(int id) {
        return partCodeMapper.toDtos(partCodeRepo.findAllByPart_Id(id));
    }

    @Override
    public List<PartCodeDto> findAllByMacDeviceCode(int id, String deviceCode) {
        return partCodeMapper.toDtos(partCodeRepo.findAllByUser_IdAndMacDeviceCodeAndCodeStatus(id, deviceCode, CodeStatus.SOLD));
    }

    @Override
    public void addAllPartCode(AddAllPartCodeDto dto) {
        for (int i = 0; i < dto.getNumber(); i++) {
            addPartCode(AddPartCodeDto.builder()
                    .partId(dto.getPartId())
                    .paid(dto.isPaid())
                    .build());
        }
    }


    @Override
    public void addPartCode(AddPartCodeDto dto) {
        String symbol;
        do {
            symbol = RandomStringUtils.random(8, "0123456789ABCDEFGHIJKLMNOPQRSTVUWXYZ");
        } while (codeRepo.existsBySymbol(symbol) ||
                partCodeRepo.existsBySymbol(symbol));

        Part part = partRepo.findById(dto.getPartId()).orElse(null);

        PartCode partCode = PartCode.builder()
                .symbol(symbol)
                .part(part)
                .codeStatus(CodeStatus.AVAILABLE)
                .paid(dto.isPaid())
                .build();
        partCodeRepo.save(partCode);
    }

    @Override
    public void activePartCode(ActiveCodeDto dto) {
        PartCode partCode = partCodeRepo.findBySymbolEquals(dto.getSymbol())
                .orElseThrow(
                        () -> new IllegalArgumentException("this code is not found")
                );

        if (partCode.getCodeStatus() != CodeStatus.AVAILABLE) {
            throw new IllegalArgumentException("this code is invalid");
        }

        Part part = partCode.getPart();
        part.setNumberOfStudent(part.getNumberOfStudent() + 1);

        User user = userRepo.findById(dto.getUserId()).get();

        partCode.setUser(user);
        partCode.setMacDeviceCode(dto.getMacDeviceCode());
        partCode.setCodeStatus(CodeStatus.SOLD);
        partCode.setEnableDate(LocalDateTime.now());

        partCodeRepo.save(partCode);
    }

//    @Override
//    public void disableAllPartCodeByDateTime(LocalDateTime dateTime) {
//        List<PartCode> partCodeList = partCodeRepo.findAllByEnableDateBefore(dateTime);
//        partCodeList.forEach(partCode -> partCode.setCodeStatus(CodeStatus.EXPIRED));
//        partCodeRepo.saveAll(partCodeList);
//
//    }

    @Override
    public void deleteAllByIds(List<Integer> ids) {
        partCodeRepo.deleteAllById(ids);
    }

    @Override
    public void deletePartCodeById(int id) {
        partCodeRepo.deleteById(id);
    }
}
