package com.provit.domain.schedule.dto;

import java.util.Optional;

public record ScheduleUpdateDto (Long id, Optional<String> title, Optional<String> content){
}
