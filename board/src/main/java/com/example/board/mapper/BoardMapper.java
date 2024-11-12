package com.example.board.mapper;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // MyBatis 매퍼 인터페이스로 지정합니다.
public interface BoardMapper {

    // 새로운 게시글의 시퀀스를 반환합니다.
    Long getSeq();

    // 새로운 게시글을 삽입합니다.
    void insertBoard(BoardVO board);

    // 특정 게시글의 상세 정보를 BoardDTO 형태로 반환합니다.
    BoardDTO selectBoardDetail(Long boardId);

    // 특정 게시글을 BoardVO 형태로 반환합니다.
    BoardVO selectBoard(long boardId);

    // 특정 게시글을 업데이트합니다.
    void updateBoard(BoardVO board);

    // 특정 게시글의 조회수를 증가시킵니다.
    void plusViews(long boardId);

    // 특정 게시글을 삭제합니다.
    void deleteBoard(Long boardId);

    // 모든 게시글을 반환합니다
    List<BoardDTO> selectAll();

    // 날짜를 기준으로 오름차순 정렬된 모든 게시글을 반환합니다.
    List<BoardDTO> selectAllByDateASC();

    // 조회수를 기준으로 모든 게시글을 반환합니다.
    List<BoardDTO> selectAllByViews();
}
