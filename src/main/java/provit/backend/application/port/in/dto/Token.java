package provit.backend.application.port.in.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Token {
    public String access_token;
    public String refresh_token;

    public Token(String access_token, String refresh_token) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
}
