package com.example.board.service;

import com.example.board.domain.dto.CommentDTO;
import com.example.board.domain.vo.CommentVO;
import java.util.List;

public interface CommentService {
    // 댓글 추가
    void addComment(CommentVO comment);

    // 특정 게시글의 모든 댓글 조회
    List<CommentDTO> getCommentsByBoardId(Long boardId);


    // 댓글 수정
    void updateComment(CommentVO comment);

    // 댓글 삭제
    void removeComment(Long commentId);
}
