package provit.backend.application.util;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
// TokenProvider는 Filter에서 사용함
public class TokenProvider {

    private String secretKey = "porvitrprovitprovitprovitprovitprovitprovitprovitprovitprovitprovitprovit";
    private long tokenValidTime = 15 * 60 * 1000L; //15분
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //토큰 생성
    public String createToken(String userPk, List<String> roles){
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        Date current = new Date();

        return Jwts.builder()
                .setClaims(claims) // payload 저장
                .setIssuedAt(current)
                .setExpiration(new Date(current.getTime()+tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey) //암호화 알고리즘, signature 키값
                .compact();
    }

    //토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUser(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    //토큰에서 유저정보 추출
    public String getUser(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    //헤더에서 토큰값 추출
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }
    //토큰 검증
    public boolean validateToken(String token){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }
}
