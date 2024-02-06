package com.provit.domain.member.dto;

import com.provit.domain.member.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberInfoDto {
	private final String username;
    private final String email;
	private final String name;
    private final String password;
    private final String birth;

    public MemberInfoDto(Member member) {
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.name = member.getName();
        this.password = member.getPassword();
        this.birth = member.getBirth();
    }
}