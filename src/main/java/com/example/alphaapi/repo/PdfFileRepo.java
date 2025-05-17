package com.example.alphaapi.repo;

import com.example.alphaapi.model.entity.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PdfFileRepo extends JpaRepository<PdfFile, String> {

    List<PdfFile> findAllByPart_Id(int id);
}
