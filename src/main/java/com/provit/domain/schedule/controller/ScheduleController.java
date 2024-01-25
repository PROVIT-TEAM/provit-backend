package com.provit.domain.schedule.controller;

import com.provit.domain.schedule.dto.ScheduleDto;
import com.provit.domain.schedule.dto.ScheduleUpdateDto;
import com.provit.domain.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/add")
    public ResponseEntity<String> addSchedule(@Valid @RequestBody ScheduleDto scheduleDto) throws Exception {
        return scheduleService.addSchedule(scheduleDto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSchedule(@RequestBody ScheduleUpdateDto updateDto) throws Exception{
        return scheduleService.updateSchedule(updateDto);
    }
}
