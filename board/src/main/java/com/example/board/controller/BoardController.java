package com.example.board.controller;

import com.example.board.domain.dto.BoardDTO;
import com.example.board.domain.oauth.CustomOAuth2User;
import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.FileVO;
import com.example.board.service.BoardService;
import com.example.board.service.FileService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final FileService fileService;

    // 생성자를 통한 BoardService 주입
    public BoardController(BoardService boardService, FileService fileService) {
        this.boardService = boardService;
        this.fileService = fileService;
    }

    @GetMapping
    public String index(){
        return "board/index";
    }

    // 게시글 목록 조회 정렬 ajax 적용한 것
    @GetMapping("/sort")
    public String sort(Model model) {
        List<BoardDTO> boards = boardService.selectAll();
        model.addAttribute("boards", boards);
        return "board/boardRestList";
    }

    // 게시글 목록 조회
    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDTO> boards = boardService.selectAll();
        model.addAttribute("boards", boards);
        return "board/boardList";
    }

    // 게시글 작성 폼
    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("boardVO", new BoardVO());
        return "board/write";
    }

    // 게시글 작성 처리
    @PostMapping("/write")
    public String write(@ModelAttribute BoardVO boardVO, @RequestParam("files") List<MultipartFile> files, @RequestParam("providerId") String providerId, RedirectAttributes redirectAttributes) {
        boardVO.setProviderId(providerId);
        boardService.insertBoard(boardVO, files);
        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 등록되었습니다.");
        return "redirect:/board/list";
    }

    // 게시글 조회
    @GetMapping("/detail/{boardId}")
    public String view(@PathVariable Long boardId, Model model, @AuthenticationPrincipal CustomOAuth2User customUser) {
        BoardDTO board = boardService.selectBoardDetail(boardId, customUser);
        List<FileVO> files = fileService.getFileListByBoardId(boardId);
        model.addAttribute("board", board);
        model.addAttribute("files", files);
        return "board/detail";
    }


    // 게시글 수정 폼
    @GetMapping("/edit/{boardId}")
    public String editForm(@PathVariable Long boardId, Model model) {
        BoardVO board = boardService.selectBoard(boardId);
        model.addAttribute("board", board);
        return "board/edit";
    }

    // 게시글 수정 처리
    @PostMapping("/update")
    public String update(BoardVO board, RedirectAttributes redirectAttributes, @RequestParam("files") List<MultipartFile> files) {
        boardService.updateBoard(board, files);
        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 수정되었습니다.");
        return "redirect:/board/list";
    }

    // 게시글 삭제 처리
    @PostMapping("/delete/{boardId}")
    public String delete(@PathVariable Long boardId, RedirectAttributes redirectAttributes) {
        System.out.println(boardId + "컨트롤러 들어옴");
        boardService.deleteBoard(boardId);
        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
        return "redirect:/board/list";
    }

}
