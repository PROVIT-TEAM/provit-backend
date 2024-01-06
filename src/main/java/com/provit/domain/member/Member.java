package com.provit.domain.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.provit.domain.BaseTimeEntity;
import com.provit.global.oauth2.SocialType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; //primary Key

    @Column(nullable = false, unique = true)
    private String username; //아이디

    private String password; //비밀번호

    @Column(nullable = false, length = 30)
    private String name; //이름
  
    @Enumerated(EnumType.STRING)
    private Role role; //권한 USER, ADMIN
    
    private String socialId;
    
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(length = 1000)
    private String refreshToken;
    
    public void updateRefreshToken(String refreshToken){
    	this.refreshToken = refreshToken;
    }
    public void destroyRefreshToken(){
    	this.refreshToken = null;
    }
     
    public void updatePassword(PasswordEncoder passwordEncoder, String password){
        this.password = passwordEncoder.encode(password);
    }

    public void updateName(String name){
        this.name = name;
    }

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }
    
   // 비밀번호 변경, 회원 탈퇴 시, 비밀번호를 확인하며, 이때 비밀번호의 일치여부를 판단하는 메서드입니다.
    public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword){
        return passwordEncoder.matches(checkPassword, getPassword());
    }

    //회원가입시, USER의 권한을 부여하는 메서드입니다.
    public void addUserAuthority() {
        this.role = Role.USER;
    }
   
}
