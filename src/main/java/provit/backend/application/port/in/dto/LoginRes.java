package provit.backend.application.port.in.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRes {
    private String token;
    private String email;

    public LoginRes(String token, String email) {
        this.token = token;
        this.email = email;
    }
}
