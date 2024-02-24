package com.provit.domain.schedule.service;

import com.provit.domain.schedule.dto.ScheduleDto;
import com.provit.domain.schedule.dto.ScheduleUpdateDto;
import org.springframework.http.ResponseEntity;

public interface ScheduleUseCase {
    public ResponseEntity<?> addSchedule(ScheduleDto scheduleDto) throws Exception;
    public ResponseEntity<?> updateSchedule(ScheduleUpdateDto updateDto) throws Exception;
    public ResponseEntity<String> deleteSchedule(Long id) throws Exception;
    public ResponseEntity<?> getScheduleList() throws Exception;
}
