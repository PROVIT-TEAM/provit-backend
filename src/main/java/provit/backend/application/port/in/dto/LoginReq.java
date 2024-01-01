package provit.backend.application.port.in.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginReq {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
