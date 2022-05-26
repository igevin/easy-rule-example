package com.igevin.easyruleexample.progress.forewarning.launcher;

import com.igevin.easyruleexample.progress.forewarning.ForewarningTask;

import java.util.ArrayList;
import java.util.List;

public class LauncherHelper {
    public static List<ForewarningTask> createTasks() {
        List<ForewarningTask> tasks = new ArrayList<>();

        tasks.add(ForewarningTask.builder()
                .id(1L)
                .completionRate(80d)
                .estimatedDelayDays(0d)
                .plannedRemainingDays(1d)
                .build());
        tasks.add(ForewarningTask.builder()
                .id(2L)
                .completionRate(90d)
                .estimatedDelayDays(4d)
                .plannedRemainingDays(1d)
                .build());
        tasks.add(ForewarningTask.builder()
                .id(3L)
                .completionRate(80d)
                .estimatedDelayDays(0d)
                .plannedRemainingDays(10d)
                .build());
        tasks.add(ForewarningTask.builder()
                .id(4L)
                .completionRate(20d)
                .estimatedDelayDays(10d)
                .plannedRemainingDays(5d)
                .build());
        tasks.add(ForewarningTask.builder()
                .id(5L)
                .completionRate(80d)
                .estimatedDelayDays(3d)
                .plannedRemainingDays(1d)
                .build());
        tasks.add(ForewarningTask.builder()
                .id(6L)
                .completionRate(95d)
                .estimatedDelayDays(2d)
                .plannedRemainingDays(1d)
                .build());
        tasks.add(ForewarningTask.builder()
                .id(7L)
                .completionRate(95d)
                .estimatedDelayDays(2d)
                .plannedRemainingDays(0d)
                .build());
        return tasks;
    }
}
