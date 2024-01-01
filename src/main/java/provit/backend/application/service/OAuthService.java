package provit.backend.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import provit.backend.application.port.in.OAuth2UseCase;
import provit.backend.application.port.in.dto.GoogleUser;
import provit.backend.application.port.in.dto.UserDto;
import provit.backend.application.port.out.CommandUserPort;
import provit.backend.domain.User;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService implements OAuth2UseCase {
    private final CommandUserPort commandUserPort;

    @Value("${OAuth2.google.ClientId}")
    private String GOOGLE_CLIENT;
    @Value("${OAuth2.google.ClientSecret}")
    private String GOOGLE_SECRET;
    @Value("${OAuth2.google.LoginUrl}")
    private String GOOGLE_LOGIN;
    @Value("${OAuth2.google.RedirectUrl}")
    private String GOOGLE_REDIRECT;
    @Value("${OAuth2.google.TokenUrl}")
    private String GOOGLE_TOKEN;
    @Value("${OAuth2.google.UserInfoUrl}")
    private String GOOGLE_USER_INFO;


    @Value("${OAuth2.kakao.ClientId}")
    String KAKAO_CLIENT;
    @Value("${OAuth2.kakao.ClientSeceret}")
    String KAKAO_SECRET;
    @Value("${OAuth2.kakao.LoginUrl}")
    String KAKAO_LOGIN;
    @Value("${OAuth2.kakao.RedirectUrl}")
    String KAKAO_REDIRECT;
    @Value("${OAuth2.kakao.TokenUrl}")
    String KAKAO_TOKEN;
    @Value("${OAuth2.kakao.UserInfoUrl}")
    String KAKAO_USER_INFO;

    @Value("${OAuth2.naver.ClientId}")
    String NAVER_CLIENT;
    @Value("${OAuth2.naver.ClientSecret}")
    String NAVER_SECRET;
    @Value("${OAuth2.naver.Authorize}")
    String NAVER_LOGIN;
    @Value("${OAuth2.naver.Redirect}")
    String NAVER_REDIRECT;
    @Value("${OAuth2.naver.Token}")
    String NAVER_TOKEN;
    @Value("${OAuth2.naver.UserInfo}")
    String NAVER_USER_INFO;


    @Override
    public String kakao() {
        return UriComponentsBuilder.fromUriString(KAKAO_LOGIN)
                .queryParam("client_id", KAKAO_CLIENT)
                .queryParam("redirect_uri", KAKAO_REDIRECT)
                .queryParam("response_type", "code")
                .queryParam("scope", "openid")
                .queryParam("prompt", "select_account")
                .toUriString();
    }

    @Override
    public String KakaoAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", KAKAO_CLIENT);
        params.add("redirect_uri", KAKAO_REDIRECT);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(KAKAO_TOKEN, HttpMethod.POST, request, String.class);
        log.info("response: "+response.toString());

        try {
            JSONObject jsonoBject = new JSONObject(response.getBody());
            String access_token = (String)jsonoBject.get("access_token");
            String refresh_token = (String)jsonoBject.get("refresh_token");
            log.info("access_token: "+access_token);
            log.info("refresh_token: "+refresh_token);
            log.info(getUserInfo(access_token).toString());
//            registOrLogin(getUserInfo(access_token)); //kakao 가입 db저장 테스트
            return access_token;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String naver() {
        return UriComponentsBuilder
                .fromUriString(NAVER_LOGIN)
                .queryParam("response_type", "code")
                .queryParam("client_id", NAVER_CLIENT)
                .queryParam("redirect_uri", NAVER_REDIRECT)
                .queryParam("state", "provitprovit")
                .toUriString();
    }
    @Override
    public String NaverAccessToken(String state, String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", NAVER_CLIENT);
        params.add("client_secret", NAVER_SECRET);
        params.add("state", state);
        params.add("code", code);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(NAVER_TOKEN, params, String.class);
        log.info("response: "+response.toString());

        try {
            JSONObject jsonoBject = new JSONObject(response.getBody());
            String access_token = (String)jsonoBject.get("access_token");
            String refresh_token = (String)jsonoBject.get("refresh_token");
            log.info("access_token: "+access_token);
            log.info("refresh_token: "+refresh_token);
            log.info(getUserInfo(access_token).toString());
//            registOrLogin(getUserInfo(access_token)); //kakao 가입 db저장 테스트
            return access_token;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String google() {
        return UriComponentsBuilder.fromUriString(GOOGLE_LOGIN)
                .queryParam("client_id", GOOGLE_CLIENT)
                .queryParam("redirect_uri", GOOGLE_REDIRECT)
                .queryParam("response_type", "code") //code 고정
                .queryParam("scope", "openid email profile") //openid email profile
                .queryParam("access_type", "offline")
                .queryParam("prompt", "select_account")
                .toUriString();
    }
    @Override //access token 추출
    public String GoogleAccessToken(String code){
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", GOOGLE_CLIENT);
        params.put("client_secret", GOOGLE_SECRET);
        params.put("redirect_uri", GOOGLE_REDIRECT);
        params.put("grant_type", "authorization_code");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(GOOGLE_TOKEN, params, String.class);
        log.info("response: "+response.toString());
        try {
            JSONObject jsonoBject = new JSONObject(response.getBody());
            String access_token = (String)jsonoBject.get("access_token");
            log.info("access_token: "+access_token);
            log.info(getUserInfo(access_token).toString());

//            registOrLogin(getUserInfo(access_token)); //google 가입 db저장 테스트
            return access_token;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Override //access_token으로 유저정보 요청
    public ResponseEntity<String> getUserInfo(String access_token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+access_token);
//        Content-type: application/x-www-form-urlencoded;charset=utf-8
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(GOOGLE_USER_INFO, HttpMethod.GET, httpEntity, String.class);

        return response;
    }

    @Override //db에 없으면 저장, 있으면 로그인 처리
    public UserDto registOrLogin(ResponseEntity<GoogleUser> response) {
        GoogleUser user = response.getBody();
        commandUserPort.registUser(new User(user.getEmail(), user.getName(), user.getEmail(), "", "ok"));
        return null;
    }
}
