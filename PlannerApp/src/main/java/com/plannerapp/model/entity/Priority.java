package com.plannerapp.model.entity;

import com.plannerapp.model.enums.PriorityTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private PriorityTypeEnum name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "priority", cascade = CascadeType.ALL)
    private List<Task> tasks;
}
