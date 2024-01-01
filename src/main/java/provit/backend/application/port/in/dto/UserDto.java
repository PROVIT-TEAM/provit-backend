package provit.backend.application.port.in.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import provit.backend.adapter.out.persistence.entity.UserEntity;
import provit.backend.domain.User;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserDto {

    /*
    * UseCase의 Dto(필드 값) + 검증 로직
    * 외부 입력값을 일정한 형태로 포맷하는 느낌
    * Service Layer의 파라미터로 사용됨
    * */
    @NotBlank
    private final String email;
    @NotBlank
    private final String name;
    @NotBlank
    private final String password;
    @NotBlank
    private final String birth;
    private final String marketing;
    private List<String> roles = new ArrayList<>();
    private String refresh;

    public static UserDto from(UserEntity user) {
       if (user == null) {
           return null;
       }

       return UserDto.builder()
               .email(user.getEmail())
               .name(user.getName())
               .password(user.getPassword())
               .birth(user.getBirth())
               .marketing(user.getMarketing())
               .build();
    }
}
