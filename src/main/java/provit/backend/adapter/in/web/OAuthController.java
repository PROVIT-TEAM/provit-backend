package provit.backend.adapter.in.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import provit.backend.application.service.OAuthService;

import java.io.IOException;

@RestController
@RequestMapping("/oauth2")
@RequiredArgsConstructor
@Slf4j
public class OAuthController {
    private final OAuthService oAuthService;

    @GetMapping("/callback/google")
    public ResponseEntity<String> callback(HttpServletRequest req, HttpServletResponse res){
        log.info("req:"+req.getParameter("code"));
        return oAuthService.getAccessToken(req.getParameter("code"));
    }

    @GetMapping("/google")
    public void getGoogle(HttpServletRequest request, HttpServletResponse response){
        try {
            response.sendRedirect(oAuthService.getGoogleLoginUrl());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
