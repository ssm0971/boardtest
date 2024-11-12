package com.example.board.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class UsersVO {
    private String name; // 사용자 이름
    private String profilePic; // 프로필 사진 URL
    private String provider; // 인증 제공자 (예: google)
    private String providerId; // 제공자의 사용자 고유 ID
    private LocalDateTime createAt; // 생성 시간
    private LocalDateTime updateAt; // 수정 시간
}
