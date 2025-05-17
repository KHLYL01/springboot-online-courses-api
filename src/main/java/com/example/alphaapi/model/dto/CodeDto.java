package com.example.alphaapi.model.dto;

import com.example.alphaapi.model.enums.CodeStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class CodeDto {

    private int id;
    private String symbol;
    private int codeGroupId;
    private String codeGroupCourseIds;
    private int userId;
    private String username;
    private String userEmail;
    private CodeStatus codeStatus;
    private LocalDateTime enableDate;

}
