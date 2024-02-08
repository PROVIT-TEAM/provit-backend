package com.provit.global.oauth2.controller;

import java.util.Map;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.provit.domain.member.Member;
import com.provit.domain.member.Role;
import com.provit.domain.member.repository.MemberRepository;
import com.provit.global.oauth2.KakaoOAuth2UserInfo;
import com.provit.global.oauth2.OAuth2UserInfo;
import com.provit.global.oauth2.SocialType;
import com.provit.global.security.service.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "3. O-AUTH2", description = "O-AUTH2 서비스 API")
public class OAuthController {
	private final MemberRepository memberRepository;
	private final JwtService jwtService;
	
	@PostMapping("/oauth/kakao")
	public String jwtCreate(@RequestBody Map<String, Object> data) {
		log.info("kakao");
		OAuth2UserInfo kakaoUser = 
				new KakaoOAuth2UserInfo((Map<String, Object>)data.get("data"));
		
		System.out.println(data);

		System.out.println(data.get("data"));
		Member memberEntity = memberRepository.findBySocialTypeAndSocialId(SocialType.KAKAO, kakaoUser.getId());
		
		log.info(kakaoUser.getId());
		
		if(memberEntity == null) {
			log.info("create user");
			Member member = Member.builder()
					.username("user@socialUser.com")
					.name(kakaoUser.getNickname())
					.socialType(SocialType.KAKAO)
					.socialId(kakaoUser.getId())
					.role(Role.USER)
					.build();
			
			memberEntity = memberRepository.save(member);
		}

        String accessToken = jwtService.createAccessToken(memberEntity.getUsername());
        
		return accessToken;
	}
}
