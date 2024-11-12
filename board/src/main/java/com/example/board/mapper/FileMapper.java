package com.example.board.mapper;

import com.example.board.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // MyBatis 매퍼 인터페이스로 지정합니다.
public interface FileMapper {

    // 파일 정보를 삽입합니다.
    void insertFile(FileVO fileVO);

    // 특정 게시글에 첨부된 파일 리스트를 반환합니다.
    List<FileVO> getFileListByBoardId(Long boardId);

    // 특정 파일 ID로 파일 정보를 반환합니다.
    FileVO getFileById(Long fileId);

    // 특정 게시글에 첨부된 모든 파일을 삭제합니다.
    void deleteFiles(long boardId);
}