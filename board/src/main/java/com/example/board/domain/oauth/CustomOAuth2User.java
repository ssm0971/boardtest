package com.example.board.domain.oauth;

// Spring Security에서 가져온 클래스들을 임포트
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;


// OAuth2User 인터페이스를 구현하는 CustomOAuth2User 클래스 정의
public class CustomOAuth2User implements OAuth2User {
    // 원래의 OAuth2User 인스턴스를 저장하기 위한 변수
    private OAuth2User oauth2User;

    // 사용자의 이름을 저장할 변수
    private String name;

    // 사용자의 프로필 사진 URL 또는 경로를 저장할 변수
    private String profilePic;

    // 제공자 ID (예: Google, Facebook 등)를 저장할 변수
    private String providerId;

    // CustomOAuth2User 생성자: 매개변수로 OAuth2User, 이름, 프로필 사진, 제공자 ID를 받음
    public CustomOAuth2User(OAuth2User oauth2User, String name, String profilePic, String providerId) {
        this.oauth2User = oauth2User; // 원래의 OAuth2User 인스턴스를 저장
        this.name = name;            // 사용자의 이름을 저장
        this.profilePic = profilePic; // 사용자의 프로필 사진을 저장
        this.providerId = providerId; // 제공자 ID를 저장
    }

    // OAuth2User 인터페이스의 getAttributes 메서드를 구현
    @Override
    public Map<String, Object> getAttributes() {
        // 원래의 OAuth2User 인스턴스에서 속성들을 반환
        return oauth2User.getAttributes();
    }

    // OAuth2User 인터페이스의 getAuthorities 메서드를 구현
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 원래의 OAuth2User 인스턴스에서 권한들을 반환
        return oauth2User.getAuthorities();
    }

    // OAuth2User 인터페이스의 getName 메서드를 구현
    @Override
    public String getName() {
        // 저장된 사용자의 이름을 반환
        return this.name;
    }

    // 프로필 사진 URL 또는 경로를 반환하는 메서드
    public String getProfilePic() {
        return profilePic;
    }

    // 제공자 ID를 반환하는 메서드
    public String getProviderId() {
        return providerId;
    }
}