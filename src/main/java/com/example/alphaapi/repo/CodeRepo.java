package com.example.alphaapi.repo;

import com.example.alphaapi.model.entity.Code;
import com.example.alphaapi.model.entity.PartCode;
import com.example.alphaapi.model.enums.CodeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CodeRepo extends JpaRepository<Code, Integer> {

    boolean existsBySymbol(String symbol);

    Optional<Code> findBySymbolEquals(String symbol);

    List<Code> findAllByUser_IdAndMacDeviceCodeAndCodeStatus(int id, String deviceCode, CodeStatus codeStatus);

    List<Code> findAllByEnableDateBefore(LocalDateTime localDateTime);

    List<Code> findAllByCodeGroup_Id(int group_id);

}
