package com.provit.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleDto {

    private final Long id;
    @NotBlank
    private final String title;
    private final String content;
    @NotBlank
    private final String startDate;
    @NotBlank
    private final String endDate;

}
