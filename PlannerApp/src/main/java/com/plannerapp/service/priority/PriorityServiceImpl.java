package com.plannerapp.service.priority;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.enums.PriorityTypeEnum;
import com.plannerapp.repo.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService{
    private final PriorityRepository priorityRepository;

    @Autowired
    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @PostConstruct
    @Override
    public void dbInit() {
        if(!isDbInit()) {

            List<Priority> conditions = new ArrayList<>();
            conditions.add(Priority.builder().name(PriorityTypeEnum.URGENT).description(PriorityTypeEnum.URGENT.string).build());
            conditions.add(Priority.builder().name(PriorityTypeEnum.IMPORTANT).description(PriorityTypeEnum.IMPORTANT.string).build());
            conditions.add(Priority.builder().name(PriorityTypeEnum.LOW).description(PriorityTypeEnum.LOW.string).build());


            this.priorityRepository.saveAllAndFlush(conditions);
        }
    }

    @Override
    public boolean isDbInit() {
        return this.priorityRepository.count() > 0;
    }
}
