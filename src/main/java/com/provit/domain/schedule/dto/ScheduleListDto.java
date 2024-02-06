package com.provit.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleListDto {
    public List<ScheduleDto> ScheduleList;
}
