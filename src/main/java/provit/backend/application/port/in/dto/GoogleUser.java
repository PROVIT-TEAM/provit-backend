package provit.backend.application.port.in.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleUser {

    private String id;
    private String email;
    private String verified_email;
    private String name;
    private String given_name;
    private String picture;
    private String locale;

}
