package provit.backend.application.port.in;

import provit.backend.application.port.in.dto.LoginReq;
import provit.backend.application.port.in.dto.LoginRes;

public interface LoginUseCase {

    LoginRes login(LoginReq req);
}
