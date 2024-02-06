package com.provit.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.provit.domain.member.Member;
import com.provit.global.oauth2.SocialType;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUsername(String username);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByUsername(String username);
    
    Optional<Member> findByRefreshToken(String refreshToken);
    Member findBySocialTypeAndSocialId(SocialType socialType , String socialId);
}
