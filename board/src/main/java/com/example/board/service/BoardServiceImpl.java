package com.example.board.service;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.oauth.CustomOAuth2User;
import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.FileVO;
import com.example.board.mapper.BoardMapper;
import com.example.board.mapper.FileMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;

    public BoardServiceImpl(BoardMapper boardMapper,
                            FileMapper fileMapper) {
        this.boardMapper = boardMapper;
        this.fileMapper = fileMapper;
    }


    @Override
    @Transactional
    public void insertBoard(BoardVO boardVO, List<MultipartFile> files) {
        Long boardId = boardMapper.getSeq();
        boardVO.setBoardId(boardId);
        System.out.println(boardVO);
        boardMapper.insertBoard(boardVO); // 게시글 정보 저장


        for (MultipartFile file : files) {
            if (file.isEmpty()) continue; // 파일이 비어있으면 건너뜀

            String originalFileName = file.getOriginalFilename();
            String storedFileName = UUID.randomUUID().toString().
                    replaceAll("-", "") + "_" + originalFileName;
            Long fileSize = file.getSize();

            try {
                // 파일 저장 경로 설정
                Path path = Paths.get("C:/upload/board/" + storedFileName);
                // 파일 저장
                Files.copy(file.getInputStream(), path);

                FileVO fileVO = new FileVO();
                fileVO.setBoardId(boardId);
                fileVO.setOriginalFileName(originalFileName);
                fileVO.setStoredFileName(storedFileName);
                fileVO.setFileSize(fileSize);
                System.out.println(fileVO);
                fileMapper.insertFile(fileVO); // 파일 정보 저장
            } catch (IOException e) {
                e.printStackTrace();
                // 여기서 IOException을 처리하거나,
                // 사용자 정의 예외를 던져 상위에서 처리할 수 있습니다.
            }
        }
    }

    @Override
    public BoardDTO selectBoardDetail(Long boardId, CustomOAuth2User customUser) {

        String boardProviderId = boardMapper.selectBoard(boardId).getProviderId();

        if (customUser == null || !customUser.getProviderId().equals(boardProviderId)) {
            // 조회 수+1
            boardMapper.plusViews(boardId);
        }

        return boardMapper.selectBoardDetail(boardId);
    }

    @Override
    public BoardVO selectBoard(long boardId) {
        return boardMapper.selectBoard(boardId);
    }


    @Override
    @Transactional
    public void updateBoard(BoardVO board, List<MultipartFile> files) {
        boardMapper.updateBoard(board);
        fileMapper.deleteFiles(board.getBoardId());

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue; // 파일이 비어있으면 건너뜀

            String originalFileName = file.getOriginalFilename();
            String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFileName;
            Long fileSize = file.getSize();

            try {
                // 파일 저장 경로 설정
                Path path = Paths.get("C:/upload/board/" + storedFileName);
                // 파일 저장
                Files.copy(file.getInputStream(), path);

                FileVO fileVO = new FileVO();
                fileVO.setBoardId(board.getBoardId());
                fileVO.setOriginalFileName(originalFileName);
                fileVO.setStoredFileName(storedFileName);
                fileVO.setFileSize(fileSize);
                System.out.println(fileVO);
                fileMapper.insertFile(fileVO); // 파일 정보 저장
            } catch (IOException e) {
                e.printStackTrace();
                // 여기서 IOException을 처리하거나, 사용자 정의 예외를 던져 상위에서 처리할 수 있습니다.
            }
        }
    }


    @Override
    @Transactional
    public void deleteBoard(Long boardId) {
        boardMapper.deleteBoard(boardId);
    }

    @Override
    @Transactional
    public List<BoardDTO> selectAll() {
        return boardMapper.selectAll();
    }

    @Override
    public List<BoardDTO> selectAllByDateASC() {
        return boardMapper.selectAllByDateASC();
    }

    @Override
    public List<BoardDTO> selectAllByViews() {
        return boardMapper.selectAllByViews();
    }

}

