package provit.backend.application.port.in;

import provit.backend.application.port.in.dto.UserDto;

public interface RegistUseCase {
    /*해당 UseCase의 대략적인 method 선언*/
    UserDto registUser(UserDto userDto);
}
