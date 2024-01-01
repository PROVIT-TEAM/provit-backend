package provit.backend.application.port.in;

import org.springframework.http.ResponseEntity;
import provit.backend.application.port.in.dto.GoogleUser;
import provit.backend.application.port.in.dto.UserDto;

public interface OAuth2UseCase {
    String google();
    String kakao();
    String naver();
    String GoogleAccessToken(String code); //google 인가코드
    String KakaoAccessToken(String code); //kakao
    String NaverAccessToken(String state, String code); //naver
    ResponseEntity<String> getUserInfo(String access_token);
    UserDto registOrLogin(ResponseEntity<GoogleUser> response);
}
