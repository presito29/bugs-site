package com.plannerapp.model;

import com.plannerapp.model.enums.PriorityTypeEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskViewModel {

    private Long id;

    private String description;

    private String user;

    private PriorityTypeEnum priority;

    private LocalDate dueDate;

}