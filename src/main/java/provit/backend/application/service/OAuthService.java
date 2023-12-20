package provit.backend.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuthService {

    @Value("${OAuth2.google.Google_Token_Url}")
    private String Google_Token_Url;
    @Value("${OAuth2.google.LoginUrl}")
    private String Google_Login_Url;
    @Value("${OAuth2.google.GoogleRedirectUrl}")
    private String googleRedirectUrl;
    @Value("${OAuth2.google.GoogleClientId}")
    private String googleClientId;
    @Value("${OAuth2.google.GoogleClientSecret}")
    private String googleClientSecret;

    public String getGoogleLoginUrl(){
        return UriComponentsBuilder.fromUriString(Google_Login_Url)
                .queryParam("client_id", googleClientId)
                .queryParam("redirect_uri", googleRedirectUrl)
                .queryParam("response_type", "code")
                .queryParam("scope", "email profile openid")
                .toUriString();
    }

    public ResponseEntity<String> getAccessToken(String token){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();

        params.put("code", token);
        params.put("client_id", googleClientId);
        params.put("client_secret", googleClientSecret);
        params.put("redirect_uri", googleRedirectUrl);
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> response = restTemplate.postForEntity(Google_Token_Url, params, String.class);
        return response;
    }
}
