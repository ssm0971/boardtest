package com.example.board.service;

import com.example.board.domain.vo.FileVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {
    List<FileVO> getFileListByBoardId(Long boardId);
    FileVO getFileById(Long fileId);
}
