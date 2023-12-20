package provit.backend.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import provit.backend.adapter.out.persistence.entity.UserEntity;
import provit.backend.adapter.out.persistence.repository.UserRepository;
import provit.backend.application.port.in.dto.UserDto;
import provit.backend.application.port.out.CommandUserPort;
import provit.backend.domain.User;

import java.util.Collections;


@Component
@RequiredArgsConstructor
public class LoginPersistenceAdapter implements CommandUserPort {
    private final UserRepository userRepository;
    /*
    * domain 객체를 영속성 객체로 포맷하고 사용하면 됨
    * */
    @Override
    public UserDto registUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).orElse(null) !=null ){
            throw new RuntimeException("이미 등록된 유저");
        }

        UserEntity User = UserEntity.builder()
                .name(user.getName())
                .email(user.getEmail())
                .userId(user.getUserId())
                .password(user.getPassword())
                .birth(user.getBirth())
                .marketing(user.getMarketing())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        return UserDto.from(userRepository.save(User));
    }
    @Override
    public UserDto login(String email, String password) {
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        UserDto dto = UserDto.from(user);
        return dto;
    }

}
