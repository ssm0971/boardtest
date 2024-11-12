package com.example.board.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class FileVO {
    private Long fileId;
    private String originalFileName;
    private String storedFileName;
    private Long fileSize;
    private LocalDateTime uploadTime;
    private Long boardId;

}
