package com.provit.domain.member.dto;

import com.provit.domain.member.Member;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberInfoDto {
	private final String username;
	private final String name;

	@Builder
    public MemberInfoDto(Member member) {
        this.name = member.getName();
        this.username = member.getUsername();
    }
}