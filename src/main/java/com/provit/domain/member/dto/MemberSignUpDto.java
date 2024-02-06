package com.provit.domain.member.dto;

import com.provit.domain.member.Member;

public record MemberSignUpDto(String username, String email,  String name, String password, String birth) {
	public Member toEntity() {

		return Member.builder()
				.username(username)
				.email(email)
				.name(name)
				.password(password)
				.birth(birth)
				.build();
	}
}
