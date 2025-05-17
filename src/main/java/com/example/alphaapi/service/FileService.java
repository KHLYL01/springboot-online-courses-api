package com.example.alphaapi.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String uploadFile(MultipartFile file) throws IOException;

    byte[] viewFile(String fileName) throws IOException;

    String deleteFile(String fileName) throws IOException;

}
