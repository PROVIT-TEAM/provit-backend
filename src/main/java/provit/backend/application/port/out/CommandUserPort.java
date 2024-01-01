package provit.backend.application.port.out;

import provit.backend.application.port.in.dto.UserDto;
import provit.backend.domain.User;

/*의존성이 역전되는 구간*/
public interface CommandUserPort {
    public UserDto findByEmail(String email);
    public UserDto registUser(User user);
    public void updateToken(UserDto user);
    public UserDto login(String email, String password);
}
