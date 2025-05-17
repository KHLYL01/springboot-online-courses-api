package com.example.alphaapi.repo;

import com.example.alphaapi.model.entity.CodeGroup;
import com.example.alphaapi.model.entity.PartCode;
import com.example.alphaapi.model.enums.CodeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CodeGroupRepo extends JpaRepository<CodeGroup, Integer> {

}
