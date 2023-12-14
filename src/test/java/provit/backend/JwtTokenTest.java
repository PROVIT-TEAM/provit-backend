package provit.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import provit.backend.application.port.in.LoginUseCase;
import provit.backend.application.service.UserService;
import provit.backend.domain.User;

@SpringBootTest
public class JwtTokenTest {
    /*api 요청 받으면 토큰 생성후, 토큰 정보를 출력*/
    @Autowired
    LoginUseCase loginUseCase;
    @Autowired
    UserService userService;


    @Test
    @Transactional
    public void login(){
        String email = "zeze123@naver.com";
        String pwd = "비밀번호1";
        User user = new User("zeze123@naver.com",
                "wonzae",
                "zeze123",
                "비밀번호1",
                "970708",
                "ok");

//        System.out.print(jwtUtil.createToken(email));
    }

}
