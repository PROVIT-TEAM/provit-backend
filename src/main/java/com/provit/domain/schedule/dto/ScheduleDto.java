package com.provit.domain.schedule.dto;

import com.provit.domain.schedule.Schedule;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    Long id;
    @NotBlank
    String title;
    String content;
    @NotBlank
    String startDate;
    @NotBlank
    String endDate;

    public ScheduleDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.startDate = schedule.getStartDate();
        this.endDate = schedule.getEndDate();
    }
}
