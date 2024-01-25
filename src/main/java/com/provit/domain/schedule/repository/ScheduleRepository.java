package com.provit.domain.schedule.repository;

import com.provit.domain.member.Member;
import com.provit.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findById(Long id);
    Optional<List<Schedule>> findByMember(Member member);
}
