package provit.backend.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import provit.backend.adapter.out.persistence.entity.UserEntity;
import provit.backend.application.port.in.LoginUseCase;
import provit.backend.application.port.in.dto.LoginReq;
import provit.backend.application.port.in.dto.UserDto;
import provit.backend.application.port.in.RegistUseCase;
import provit.backend.application.port.out.CommandUserPort;
import provit.backend.application.util.TokenProvider;
import provit.backend.domain.User;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService implements RegistUseCase, LoginUseCase {
    private final CommandUserPort commandUserPort;
    private final TokenProvider tokenProvider;
//    private final PasswordEncoder passwordEncoder; 암호화 필요하면 사용
    /*
     * 실질적인 서비스 로직 구현
     *
     * 입력,출력 포트를 포함
     * 입출력 포트는 web/out/'영속성 Adapter'와 인터랙션
     * */

    @Override
    public UserDto registUser(UserDto userDto) {
        User user = new User(
                userDto.getEmail(),
                userDto.getName(),
                userDto.getUserId(),
                userDto.getPassword(),
                userDto.getBirth(),
                userDto.getMarketing());
        return commandUserPort.registUser(user);
    }
    @Override
    public String login(LoginReq req) {

        UserDto isUser = commandUserPort.login(req.getUsername(), req.getPassword());
        if (isUser == null){
            log.info("가입되지 않은 E-MAIL 입니다.");
            return "가입되지 않은 E-MAIL 입니다.";
//            throw new IllegalArgumentException("가입되지 않은 E-MAIL 입니다.");
        }
        if (!req.getPassword().equals(isUser.getPassword())){
            log.info("잘못된 비밀번호입니다.");
            return "잘못된 비밀번호입니다.";
//            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return tokenProvider.createToken(isUser.getEmail(), isUser.getRoles());
    }
}
