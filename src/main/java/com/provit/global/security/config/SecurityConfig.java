package com.provit.global.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provit.domain.member.repository.MemberRepository;
import com.provit.global.oauth2.handler.OAuth2LoginFailureHandler;
import com.provit.global.oauth2.handler.OAuth2LoginSuccessHandler;
import com.provit.global.oauth2.service.CustomOAuth2UserService;
import com.provit.global.security.filter.JsonUsernamePasswordAuthenticationFilter;
import com.provit.global.security.filter.JwtAuthenticationProcessingFilter;
import com.provit.global.security.handler.LoginFailureHandler;
import com.provit.global.security.handler.LoginSuccessJWTProvideHandler;
import com.provit.global.security.service.JwtService;
import com.provit.global.security.service.LoginService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class SecurityConfig {
	private final LoginService loginService;
	private final ObjectMapper objectMapper;
	private final MemberRepository memberRepository;
	private final JwtService jwtService;
	private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
	private final CustomOAuth2UserService customOAuth2UserService;

	@Bean
	public AuthenticationManager authenticationManager() {// AuthenticationManager 등록
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); // DaoAuthenticationProvider 사용
		provider.setPasswordEncoder(passwordEncoder()); // PasswordEncoder로는
														// PasswordEncoderFactories.createDelegatingPasswordEncoder() 사용
		provider.setUserDetailsService(loginService);
		return new ProviderManager(provider);
	}

	@Bean
	public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordLoginFilter() {
		JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordLoginFilter = new JsonUsernamePasswordAuthenticationFilter(
				objectMapper);
		jsonUsernamePasswordLoginFilter.setAuthenticationManager(authenticationManager());
		jsonUsernamePasswordLoginFilter.setAuthenticationSuccessHandler(loginSuccessJWTProvideHandler());
		jsonUsernamePasswordLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());
		return jsonUsernamePasswordLoginFilter;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf((csrfConfig) -> csrfConfig.disable())
			.formLogin((formLogin) -> formLogin.disable())
			.httpBasic((httpBasic) -> httpBasic.disable())
			.headers((headerConfig) -> 
				headerConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable())
			).sessionManagement((sessionManagement)->
				sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			);
				
		// URL별 권한
		http.authorizeHttpRequests(auth -> 
			auth
				.requestMatchers("/", "/css/**", "/images/**", "/js/**", "/favicon.ico", "/h2-console/**").permitAll()
				.requestMatchers("/signUp", "/").permitAll()
				.anyRequest().authenticated()

		);

		http.oauth2Login((oauth)->
			oauth
			.successHandler(oAuth2LoginSuccessHandler)
			.failureHandler(oAuth2LoginFailureHandler)
			.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
//				.userInfoEndpoint()
//				.userService(customOAuth2UserService)
				
				
		); // 동의하고 계속하기를 눌렀을 때 Handler 설정
			 // 소셜 로그인 실패 시 핸들러 설정
             // customUserService 설정

		http.addFilterAfter(jsonUsernamePasswordLoginFilter(), LogoutFilter.class);
		http.addFilterBefore(jwtAuthenticationProcessingFilter(), JsonUsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
		JwtAuthenticationProcessingFilter jsonUsernamePasswordLoginFilter = new JwtAuthenticationProcessingFilter(
				jwtService, memberRepository);

		return jsonUsernamePasswordLoginFilter;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public LoginSuccessJWTProvideHandler loginSuccessJWTProvideHandler() {
		return new LoginSuccessJWTProvideHandler(jwtService, memberRepository);
	}

	@Bean
	public LoginFailureHandler loginFailureHandler() {
		return new LoginFailureHandler();
	}

}
