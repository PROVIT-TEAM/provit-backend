package provit.backend.domain;

import lombok.Getter;

@Getter
public class User {
    /*POJO 객체, 아무데도 종속되지 않아야함*/
    private Long id;
    private String email;
    private String name;
    private String password;
    private String birth;
    private String marketing;

    public User(String email, String name, String password, String birth, String marketing) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.birth = birth;
        this.marketing = marketing;
    }
}
