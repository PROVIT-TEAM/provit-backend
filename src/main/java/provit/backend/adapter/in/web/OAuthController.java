package provit.backend.adapter.in.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import provit.backend.application.port.in.OAuth2UseCase;

import java.io.IOException;

@RestController
@RequestMapping("/oauth2")
@RequiredArgsConstructor
@Slf4j
public class OAuthController {
    private final OAuth2UseCase oAuth2UseCase;

    @GetMapping("/google") //구글 로그인 페이지 요청
    public void Get_Google_Url(HttpServletResponse response) throws IOException{
        response.sendRedirect(oAuth2UseCase.google());
    }
    @GetMapping("/kakao") //카카오 로그인 페이지 요청
    public void Get_Kakao_Url(HttpServletResponse response) throws IOException{
        response.sendRedirect(oAuth2UseCase.kakao());
    }
    @GetMapping("/naver")
    public void Naver(HttpServletResponse response) throws IOException{
        response.sendRedirect(oAuth2UseCase.naver());
    }
    @GetMapping("/redirect_naver")
    public void nCallback(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        log.info("code:"+code);
        log.info("state:"+state);
        String access_token= oAuth2UseCase.NaverAccessToken(state, code);
    }
    @GetMapping("/redirect_google")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String code = request.getParameter("code");
        String access_token = oAuth2UseCase.GoogleAccessToken(code);
        response.sendRedirect("http://localhost:3000");
    }
    @GetMapping("/redirect_kakao")
    public void kCallback(HttpServletRequest request, HttpServletResponse response){
        String code = request.getParameter("code");
        log.info("kakao code: "+code);
        String access_token = oAuth2UseCase.KakaoAccessToken(code);

    }
}
