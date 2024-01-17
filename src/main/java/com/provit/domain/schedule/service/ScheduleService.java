package com.provit.domain.schedule.service;

import com.provit.domain.member.Member;
import com.provit.domain.member.repository.MemberRepository;
import com.provit.domain.schedule.Schedule;
import com.provit.domain.schedule.dto.ScheduleDto;
import com.provit.domain.schedule.repository.ScheduleRepository;
import com.provit.global.security.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    public ResponseEntity<String> addSchedule(ScheduleDto scheduleDto) throws Exception {
        Member findMember = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new Exception("회원이 없습니다"));
        log.info("scheduleDto:"+scheduleDto.toString());
        scheduleRepository.save(Schedule.builder()
                .title(scheduleDto.getTitle())
                .content(scheduleDto.getContent())
                .startDate(scheduleDto.getStartDate())
                .endDate(scheduleDto.getEndDate())
                .member(findMember)
                .build());

        return ResponseEntity.ok("등록 완료");
    }
}
