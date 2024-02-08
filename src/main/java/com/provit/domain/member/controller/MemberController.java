package com.provit.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.provit.domain.member.Member;
import com.provit.domain.member.dto.MemberInfoDto;
import com.provit.domain.member.dto.MemberSignUpDto;
import com.provit.domain.member.dto.MemberUpdateDto;
import com.provit.domain.member.dto.MemberWithdrawDto;
import com.provit.domain.member.dto.UpdatePasswordDto;
import com.provit.domain.member.service.MemberService;
import com.provit.domain.member.service.MemberServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "1. 회원가입/사용자", description = "회원가입 및 사용자 정보 관리 API")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/usernameVerify")
    @Operation(summary = "중복 유저 확인")
    public ResponseEntity<?> usernameVerify(HttpServletRequest request,
            @PathVariable("username") String username) {

        log.info(request.toString());
        boolean existsUser = memberService.existsByUsername(username);
        
        if (existsUser){
            return new ResponseEntity<>("유저 가입", HttpStatus.OK);
        }

        return new ResponseEntity<>("중복 유저",HttpStatus.BAD_REQUEST);
    }
    
    /**
     * 회원가입
     */
    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> signUp(@Valid @RequestBody MemberSignUpDto memberSignUpDto) throws Exception {
    	log.info("signup");
    	System.out.println(memberSignUpDto);
        memberService.signup(memberSignUpDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 회원정보수정
     */
    @PutMapping("/member")
    @Operation(summary = "회원정보 수정")
    public ResponseEntity<?> updateBasicInfo(@Valid @RequestBody MemberUpdateDto memberUpdateDto) throws Exception {
        log.info("member Update");
        memberService.update(memberUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 비밀번호 수정
     */
    @PutMapping("/member/password")
    @Operation(summary = "비밀번호 수정")
    public ResponseEntity<HttpStatus> updatePassword(@Valid @RequestBody UpdatePasswordDto updatePasswordDto) throws Exception {
        log.info("password Update");
        memberService.updatePassword(updatePasswordDto.checkPassword(),updatePasswordDto.toBePassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * 회원탈퇴
     */
    @DeleteMapping("/member")
    @Operation(summary = "회원탈퇴")
    public ResponseEntity<HttpStatus> withdraw() throws Exception {
        log.info("member withdraw");
        memberService.withdraw();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 회원정보조회
     */
    @GetMapping("/member/{id}")
    @Operation(summary = "회원정보 조회")
    public ResponseEntity<?> getInfo(@Valid @PathVariable("id") Long id) throws Exception {
        log.info(id.toString());
        MemberInfoDto info = memberService.getInfo(id);
        log.info("member info:"+info);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    /**
     * 내정보조회
     */
    @GetMapping("/member")
    @Operation(summary = "회원정보 조회")
    public ResponseEntity<?> getMyInfo(HttpServletResponse response) throws Exception {
        MemberInfoDto info = memberService.getMyInfo();
        log.info("member Update");
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
   
}
