package com.example.board.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class UsersDTO {
    private String name;            //사용자 이름
    private String profilePic;      //프로필 사진 URL
    private String provider;        //인증 제공자
    private String providerId;      //제공자의 사용자 고유 ID
    private LocalDate createAt;     //생성시간
    private LocalDate updateAt;     //수정시간
}
