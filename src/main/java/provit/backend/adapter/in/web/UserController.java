package provit.backend.adapter.in.web;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import provit.backend.application.port.in.LoginUseCase;
import provit.backend.application.port.in.dto.*;
import provit.backend.application.port.in.RegistUseCase;
import provit.backend.application.service.EmailService;
import provit.backend.config.jwt.TokenProvider;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final RegistUseCase registUseCase;
    private final LoginUseCase loginUseCase;
    private final TokenProvider tokenProvider;
    @PostMapping("/regist")
    public ResponseEntity<UserDto> regist(@RequestBody SignUpReq request){
        log.info(request.toString());
        UserDto userDto =  UserDto.builder()
                .email(request.email)
                .name(request.name)
                .password(request.password)
//                .password(passwordEncoder.encode(req.password)) pwd 암호화 저장
                .marketing(request.marketing)
                .build();
        return ResponseEntity.ok(registUseCase.registUser(userDto));

    }
    @PostMapping("/login")
    public LoginRes login(@RequestBody LoginReq request){
        log.info(request.getEmail()+","+request.getPassword());
        return loginUseCase.login(request);
    }
    
    
    //권한 접근 테스트
    @PostMapping("/authTest")
    public String test(HttpServletRequest request){
        log.info(request.getHeader("X-AUTH-TOKEN"));
        String token = tokenProvider.resolveToken(request);
        Authentication authentication = tokenProvider.getAuthentication(token);

        return "email:"+authentication.getName() + "\n" +
                "authentication:"+authentication;
    }

    //이메일 인증 테스트
    private final EmailService emailService;
    @GetMapping("/sendEmailTest")
    public void sendTest() throws MessagingException {
        emailService.test1();
    }
    @GetMapping("/emailConfirm")
    public String emailTest(HttpServletRequest request){
        log.info(request.toString());
        log.info("api 호출 성공");
        return "인증 완료";
    }
}
