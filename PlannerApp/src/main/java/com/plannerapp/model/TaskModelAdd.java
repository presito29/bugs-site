package com.plannerapp.model;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.enums.PriorityTypeEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskModelAdd {

    @Size(min = 2, max = 50)
    @NotNull
    private String description;

    @Future
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull
    private PriorityTypeEnum priority;
}
