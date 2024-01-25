package com.provit.domain.member.service;

import com.provit.domain.member.dto.MemberInfoDto;
import com.provit.domain.member.dto.MemberSignUpDto;
import com.provit.domain.member.dto.MemberUpdateDto;

public interface MemberService {
	boolean existsByUsername(String username);
    void signup(MemberSignUpDto memberSignUpDto) throws Exception;
    void update(MemberUpdateDto memberUpdateDto) throws Exception;
    void updatePassword(String checkPassword, String toBePassword) throws Exception;
    void withdraw() throws Exception;
    MemberInfoDto getInfo(Long id) throws Exception;
    MemberInfoDto getMyInfo() throws Exception;
}
