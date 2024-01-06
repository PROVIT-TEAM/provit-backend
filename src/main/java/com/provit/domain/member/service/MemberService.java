package com.provit.domain.member.service;

import com.provit.domain.member.dto.MemberInfoDto;
import com.provit.domain.member.dto.MemberSignUpDto;
import com.provit.domain.member.dto.MemberUpdateDto;

public interface MemberService {
    void signUp(MemberSignUpDto memberSignUpDto) throws Exception;
    void update(MemberUpdateDto memberUpdateDto) throws Exception;
    void updatePassword(String checkPassword, String toBePassword) throws Exception;
    void withdraw(String checkPassword) throws Exception;
    MemberInfoDto getInfo(Long id) throws Exception;
    MemberInfoDto getMyInfo() throws Exception;
}
