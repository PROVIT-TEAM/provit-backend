package com.provit.global.oauth2.dto;

import java.util.Map;
import java.util.UUID;

import com.provit.domain.member.Member;
import com.provit.domain.member.Role;
import com.provit.global.oauth2.GoogleOAuth2UserInfo;
import com.provit.global.oauth2.KakaoOAuth2UserInfo;
import com.provit.global.oauth2.NaverOAuth2UserInfo;
import com.provit.global.oauth2.OAuth2UserInfo;
import com.provit.global.oauth2.SocialType;
import com.provit.global.oauth2.handler.OAuth2LoginFailureHandler;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class OAuthAttributes {
	private String nameAttributeKey; // OAuth2 로그인 진행 시 키가 되는 필드 값
    private OAuth2UserInfo oauth2UserInfo; // 소셜 타입별 로그인 유저 정보(닉네임, 이메일, 프로필 사진 등등)

    @Builder
    private OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oauth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oauth2UserInfo = oauth2UserInfo;
    }

    /**
     * SocialType에 맞는 메소드 호출하여 OAuthAttributes 객체 반환
     * 파라미터 : userNameAttributeName -> OAuth2 로그인 시 키(PK)가 되는 값 / attributes : OAuth 서비스의 유저 정보들
     * 소셜별 of 메소드(ofGoogle, ofKaKao, ofNaver)들은 각각 소셜 로그인 API에서 제공하는
     * 회원의 식별값(id), attributes, nameAttributeKey를 저장 후 build
     */
    public static OAuthAttributes of(SocialType socialType,
            String userNameAttributeName, Map<String, Object> attributes) {

    	log.info("OAuth Start");
    	if (socialType == SocialType.NAVER) {
    		return ofNaver(userNameAttributeName, attributes);
    	}
    	if (socialType == SocialType.KAKAO) {
    		return ofKakao(userNameAttributeName, attributes);
    	}
    	return ofGoogle(userNameAttributeName, attributes);
    } 
    
    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                .build();
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new NaverOAuth2UserInfo(attributes))
                .build();
    }
    
    public Member toEntity(SocialType socialType, OAuth2UserInfo oauth2UserInfo) {
        return Member.builder()
                .socialType(socialType)
                .socialId(oauth2UserInfo.getId())
                .username(UUID.randomUUID() + "@socialUser.com")
                .useraccount(oauth2UserInfo.getEmail())
                .name(oauth2UserInfo.getNickname())
                .role(Role.GUEST)
                .build();
    }
}
