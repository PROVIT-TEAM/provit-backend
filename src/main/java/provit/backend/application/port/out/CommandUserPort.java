package provit.backend.application.port.out;

import provit.backend.application.port.in.dto.UserDto;
import provit.backend.domain.User;

public interface CommandUserPort {
    /*의존성이 역전되는 구간*/
    public UserDto registUser(User user);
    public UserDto login(String email, String password);
}
