package com.provit.domain.schedule.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleDto {

    private final String title;
    private final String description;
    private final String startDate;
    private final String endDate;

}
