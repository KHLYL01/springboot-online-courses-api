package com.example.alphaapi.repo;

import com.example.alphaapi.model.entity.PartCode;
import com.example.alphaapi.model.enums.CodeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PartCodeRepo extends JpaRepository<PartCode, Integer> {

    boolean existsBySymbol(String symbol);

    Optional<PartCode> findBySymbolEquals(String symbol);

    List<PartCode> findAllByUser_IdAndMacDeviceCodeAndCodeStatus(int id, String deviceCode, CodeStatus codeStatus);

    List<PartCode> findAllByEnableDateBefore(LocalDateTime localDateTime);


    List<PartCode> findAllByPart_Id(int partId);

}
