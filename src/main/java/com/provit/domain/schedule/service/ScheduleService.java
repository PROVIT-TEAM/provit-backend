package com.provit.domain.schedule.service;

import com.provit.domain.member.Member;
import com.provit.domain.member.repository.MemberRepository;
import com.provit.domain.schedule.Schedule;
import com.provit.domain.schedule.dto.ScheduleDto;
import com.provit.domain.schedule.dto.ScheduleUpdateDto;
import com.provit.domain.schedule.repository.ScheduleRepository;
import com.provit.global.security.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ScheduleService implements ScheduleUseCase{
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<String> addSchedule(ScheduleDto scheduleDto) throws Exception {
        Member findMember = memberRepository.findByEmail(SecurityUtil.getLoginUsername())
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
    @Override
    public ResponseEntity<String> updateSchedule(ScheduleUpdateDto updateDto) throws Exception {
        Schedule schedule = scheduleRepository.findById(updateDto.id()).orElseThrow(() -> new Exception("존재하지 않는 일정입니다."));
        if (updateDto.title().isPresent()){
            schedule.updateTitle(updateDto.title().get());
        }
        if (updateDto.content().isPresent()){
            schedule.updateContent(updateDto.content().get());
        }

        return ResponseEntity.ok("수정 완료");
    }
//    public ResponseEntity<String> updateTitle(Long id, String title) throws Exception{
//        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new Exception("존재하지 않는 일정입니다."));
//        schedule.updateTitle(title);
//        return ResponseEntity.ok("수정 완료");
//    }
//    public ResponseEntity<String> updateContent(Long id, String content) throws Exception{
//        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new Exception("존재하지 않는 일정입니다."));
//        schedule.updateContent(content);
//        return ResponseEntity.ok("수정 완료");
//    }
}
