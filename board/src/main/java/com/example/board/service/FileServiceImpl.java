package com.example.board.service;

import com.example.board.domain.vo.FileVO;
import com.example.board.mapper.FileMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;

    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }
    @Override
    public List<FileVO> getFileListByBoardId(Long boardId) {
        return fileMapper.getFileListByBoardId(boardId);
    }

    @Override
    public FileVO getFileById(Long fileId) {
        return fileMapper.getFileById(fileId);
    }
}
