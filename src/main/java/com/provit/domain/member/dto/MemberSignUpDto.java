package com.provit.domain.member.dto;

import com.provit.domain.member.Member;

public record MemberSignUpDto(String username, String password, String name) {
	public Member toEntity() {
		return Member.builder().username(username).password(password).name(name).build();
	}
}
