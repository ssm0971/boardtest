package com.example.board.mapper;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.vo.BoardVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class BoardMapperTest {
    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testUpdateBoard() {
        // 준비: 테스트용 게시글 객체 생성
        BoardVO board = new BoardVO();
        board.setBoardId(1L); // 존재하는 게시글 ID로 변경
        board.setBoardTitle("Updated Title");
        board.setBoardContent("Updated Content");

        // 실행: 게시글 수정 메서드 호출
        boardMapper.updateBoard(board);

        // 확인: 수정된 게시글을 다시 조회하여 확인
        BoardVO updatedBoard = boardMapper.selectBoard(board.getBoardId());
        assertEquals(board.getBoardTitle(), updatedBoard.getBoardTitle());
        assertEquals(board.getBoardContent(), updatedBoard.getBoardContent());
    }


    @Test
    public void testPlusViews() {
        // 준비: 조회 수 증가 전의 게시글 조회
        Long boardId = 1L; // 존재하는 게시글 ID로 변경
        BoardVO originalBoard = boardMapper.selectBoard(boardId);
        int originalViews = originalBoard.getBoardViews();

        // 실행: 조회 수 증가 메서드 호출
        boardMapper.plusViews(boardId);

        // 확인: 조회 수가 증가되었는지 확인
        BoardVO updatedBoard = boardMapper.selectBoard(boardId);
        assertEquals(originalViews + 1, updatedBoard.getBoardViews());
    }

    @Test
    public void testDeleteBoard() {
        // 준비: 삭제 전의 게시글 개수 조회
        List<BoardDTO> originalBoards = boardMapper.selectAll();
        int originalSize = originalBoards.size();

        // 실행: 게시글 삭제 메서드 호출
        Long boardId = 1L; // 존재하는 게시글 ID로 변경
        boardMapper.deleteBoard(boardId);

        // 확인: 삭제 후의 게시글 개수 조회하여 확인
        List<BoardDTO> updatedBoards = boardMapper.selectAll();
        assertEquals(originalSize - 1, updatedBoards.size());
    }

    @Test
    public void testSelectAllByDateASC() {
        // 실행: 최신순으로 정렬된 게시글 목록 조회
        List<BoardDTO> boards = boardMapper.selectAllByDateASC();

        // 확인: 조회된 게시글 목록이 비어있지 않은지 확인
        assertFalse(boards.isEmpty());
        System.out.println(boards);
    }

    @Test
    public void testSelectAllByViews() {
        // 실행: 조회수가 많은 순으로 정렬된 게시글 목록 조회
        List<BoardDTO> boards = boardMapper.selectAllByViews();

        // 확인: 조회된 게시글 목록이 비어있지 않은지 확인
        assertFalse(boards.isEmpty());
    }
}