package com.example.board.mapper;

import com.example.board.domain.dto.CommentDTO;
import com.example.board.domain.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 댓글 추가
    void insertComment(CommentVO comment);

    // 특정 게시글의 모든 댓글 조회
    List<CommentDTO> selectCommentsByBoardId(Long boardId);

    CommentVO selectByCommentId(Long commentId);

    // 댓글 수정
    void updateComment(CommentVO comment);

    // 댓글 삭제
    void deleteComment(Long commentId);
}
