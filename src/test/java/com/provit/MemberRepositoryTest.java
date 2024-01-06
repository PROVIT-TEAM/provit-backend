package com.provit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.provit.domain.member.Member;
import com.provit.domain.member.Role;
import com.provit.domain.member.repository.MemberRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
	
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    private void clear(){
        em.flush();
        em.clear();
    }
    
    @AfterEach
    private void after(){
        em.clear();
    }
    
    @Test
    public void 회원저장_성공() throws Exception {
        //given
        Member member = Member.builder().username("email").password("1234567890").name("Member1").role(Role.USER).build();

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(saveMember.getId()).orElseThrow(() -> new RuntimeException("저장된 회원이 없습니다"));

        assertThat(findMember).isSameAs(saveMember);
        assertThat(findMember).isSameAs(member);
    }
   
    @Test
    public void 오류_회원가입시_아이디가_없음() throws Exception {
        //given
        Member member = Member.builder().password("1234567890").name("Member1").role(Role.USER).build();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }
    

    @Test
    public void 오류_회원가입시_중복된_아이디가_있음() throws Exception {
        //given
        Member member1 = Member.builder().username("username").password("1234567890").name("Member1").role(Role.USER).build();
        Member member2 = Member.builder().username("username").password("1111111111").name("Member2").role(Role.USER).build();
       

        memberRepository.save(member1);
        clear();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member2));

    }

}
