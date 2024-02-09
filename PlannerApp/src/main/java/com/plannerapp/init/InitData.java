package com.plannerapp.init;


import com.plannerapp.service.priority.PriorityService;
import org.springframework.boot.CommandLineRunner;

public class InitData implements CommandLineRunner {
    private final PriorityService priorityService;

    public InitData(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @Override
    public void run(String... args) throws Exception {
        priorityService.dbInit();
    }
}
