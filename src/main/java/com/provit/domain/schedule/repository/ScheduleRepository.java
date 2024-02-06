package com.provit.domain.schedule.repository;

import com.provit.domain.member.Member;
import com.provit.domain.schedule.Schedule;
import com.provit.domain.schedule.dto.ScheduleDto;
import com.provit.domain.schedule.dto.ScheduleListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findById(Long id);
    Optional<List<Schedule>> findAllByMember(Member member);
    @Override
    void deleteById(Long id);
}
