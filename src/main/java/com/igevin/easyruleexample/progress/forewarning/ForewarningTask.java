package com.igevin.easyruleexample.progress.forewarning;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class ForewarningTask {
    @Getter
    private static final String name = "ForewarningTask";
    private Long id;
    private Double completionRate;
    private Double plannedRemainingDays;
    private Double estimatedDelayDays;
    private boolean forewarning;
}
