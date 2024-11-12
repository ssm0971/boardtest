package com.example.board.controller; // 패키지 선언문: 현재 클래스가 속한 패키지를 정의합니다.

import com.example.board.domain.vo.FileVO; // 다른 필요한 클래스들을 import 합니다.
import com.example.board.service.FileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController // 이 클래스가 RESTful 웹 서비스의 엔드포인트임을 나타내는 어노테이션입니다.
@RequestMapping("/download") // 이 컨트롤러의 URL 매핑을 "/download"로 설정합니다.
public class FileController { // 클래스 선언: 파일 다운로드를 처리하는 컨트롤러입니다.

    private final FileService fileService; // FileService를 주입받기 위한 멤버 변수입니다.

    public FileController(FileService fileService) { // 생성자를 통해 FileService를 주입받습니다.
        this.fileService = fileService;
    }

    @GetMapping("/{fileId}") // HTTP GET 요청을 처리하기 위한 메서드입니다. URL 경로에서 fileId를 변수로 받습니다.
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) { // 파일 다운로드 로직을 처리하는 메서드입니다.
        // 파일 정보 조회
        FileVO fileVO = fileService.getFileById(fileId); // FileService를 사용하여 파일 정보를 조회합니다.

        // 실제 파일을 불러오는 로직 (FileSystemResource 사용 등)
        Path filePath = Paths.get("C:/upload/board/" + fileVO.getStoredFileName()); // 파일이 저장된 경로를 찾습니다.
        Resource resource = new FileSystemResource(filePath); // 파일 시스템에서 파일을 Resource 객체로 로드합니다.

        if (!resource.exists()) { // 파일이 존재하는지 확인합니다.
            return ResponseEntity.notFound().build(); // 파일이 없으면 404 Not Found 응답을 반환합니다.
        }

        // 파일명 인코딩 처리 등
        String encodedFileName = URLEncoder.encode(fileVO.getOriginalFileName(), StandardCharsets.UTF_8).replace("+", "%20"); // 파일명을 URL 인코딩합니다.

        // Content-Disposition 설정
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\""; // Content-Disposition 헤더 설정하여 파일 다운로드로 처리합니다.

        return ResponseEntity.ok() // 200 OK 응답을 생성합니다.
                .contentType(MediaType.APPLICATION_OCTET_STREAM) // 응답의 콘텐츠 타입을 설정합니다.
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition) // Content-Disposition 헤더를 설정합니다.
                .body(resource); // 파일 리소스를 응답 바디에 포함합니다.
    }
}