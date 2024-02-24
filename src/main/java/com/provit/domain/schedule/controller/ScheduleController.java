package com.provit.domain.schedule.controller;

import com.provit.domain.schedule.dto.ScheduleDto;
import com.provit.domain.schedule.dto.ScheduleUpdateDto;
import com.provit.domain.schedule.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "4. 일정", description = "일정 관련 API")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    @Operation(summary = "일정 등록")
    public ResponseEntity<String> addSchedule(@Valid @RequestBody ScheduleDto scheduleDto) throws Exception {
        return scheduleService.addSchedule(scheduleDto);
    }

    @PutMapping("/schedule")
    @Operation(summary = "일정 수정")
    public ResponseEntity<?> updateSchedule(@RequestBody ScheduleUpdateDto updateDto) throws Exception{
        return scheduleService.updateSchedule(updateDto);
    }

    @DeleteMapping("/schedule/{id}")
    @Operation(summary = "일정 삭제")
    public ResponseEntity<?> deleteSchedule(@PathVariable("id") Long id) throws Exception{
        return scheduleService.deleteSchedule(id);
    }

    @GetMapping("/schedule")
    @Operation(summary = "일정 조회")
    public ResponseEntity<?> getMySchedule() throws Exception {
        return scheduleService.getScheduleList();
    }
}
