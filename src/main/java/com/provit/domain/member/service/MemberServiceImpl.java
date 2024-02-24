package com.provit.domain.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provit.domain.member.Member;
import com.provit.domain.member.dto.MemberInfoDto;
import com.provit.domain.member.dto.MemberSignUpDto;
import com.provit.domain.member.dto.MemberUpdateDto;
import com.provit.domain.member.exception.MemberException;
import com.provit.domain.member.exception.MemberExceptionType;
import com.provit.domain.member.repository.MemberRepository;
import com.provit.global.security.utils.SecurityUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

	@Override
	public boolean existsByUsername(String username){
		boolean existsUser = memberRepository.existsByUsername(username);
		return existsUser;		
	}
	
    @Override
    public void signup(MemberSignUpDto memberSignUpDto) throws Exception {
        Member member = memberSignUpDto.toEntity();
        member.addUserAuthority();
        member.encodePassword(passwordEncoder);

        if(memberRepository.findByEmail(memberSignUpDto.email()).isPresent()){
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_USERNAME);            
        }
        memberRepository.save(member);
    }

    @Override
    public void update(MemberUpdateDto memberUpdateDto) throws Exception {
        log.info(memberUpdateDto.toString());
        Member member = memberRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));
        memberUpdateDto.name().ifPresent(member::updateName);
    }

    @Override
    public void updatePassword(String checkPassword, String toBePassword) throws Exception {
        log.info(checkPassword + ", " + toBePassword);
        Member member = memberRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));
        if(!member.matchPassword(passwordEncoder, checkPassword) ) {
            throw new MemberException(MemberExceptionType.WRONG_PASSWORD);
        }
        member.updatePassword(passwordEncoder, toBePassword);
    }


    @Override
    public void withdraw() throws Exception {
        var target = SecurityUtil.getLoginUsername();
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));
//        if(!member.matchPassword(passwordEncoder, checkPassword) ) {
//            throw new MemberException(MemberExceptionType.WRONG_PASSWORD);
//        }
        memberRepository.delete(member);
    }

    @Override
    public MemberInfoDto getInfo(Long id) throws Exception {
        log.info(id.toString());
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new Exception("회원이 없습니다"));
        return new MemberInfoDto(findMember);
    }

    @Override
    public MemberInfoDto getMyInfo() throws Exception {
        var check = SecurityUtil.getLoginUsername();
        Member findMember = memberRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 없습니다"));
        return new MemberInfoDto(findMember);
    }
}
