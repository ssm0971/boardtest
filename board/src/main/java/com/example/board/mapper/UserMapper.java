package com.example.board.mapper;

import com.example.board.domain.vo.UsersVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper // MyBatis 매퍼 인터페이스로 지정합니다.
public interface UserMapper {

    // providerId를 기준으로 사용자 정보를 조회합니다.
    UsersVO findByProviderId(String providerId);

    // 새로운 사용자를 삽입합니다.
    void insertUser(UsersVO user);

    // 기존 사용자 정보를 업데이트합니다.
    void updateUser(UsersVO user);
}