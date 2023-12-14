package provit.backend.application.port.in;

import provit.backend.application.port.in.dto.LoginReq;

public interface LoginUseCase {

    String login(LoginReq req);
}
