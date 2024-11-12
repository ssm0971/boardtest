package com.example.board.service;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.oauth.CustomOAuth2User;
import com.example.board.domain.vo.BoardVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service // 서비스 클래스임을 표시합니다.
public interface BoardService {

    // 게시글과 첨부 파일들을 삽입합니다.
    void insertBoard(BoardVO board, List<MultipartFile> files);

    // 상세보기 페이지로 이동할 때 게시글 상세 정보를 조회합니다.
    BoardDTO selectBoardDetail(Long boardId, CustomOAuth2User customUser);

    // 특정 게시글 정보를 조회합니다.
    BoardVO selectBoard(long boardId);

    // 게시글과 첨부 파일들을 업데이트합니다.
    void updateBoard(BoardVO board, List<MultipartFile> files);

    // 특정 게시글을 삭제합니다.
    void deleteBoard(Long boardId);

    // 모든 게시글을 조회합니다.
    List<BoardDTO> selectAll();

    // 모든 게시글을 날짜 오름차순으로 조회합니다.
    List<BoardDTO> selectAllByDateASC();

    // 모든 게시글을 조회수 기준으로 조회합니다.
    List<BoardDTO> selectAllByViews();
}