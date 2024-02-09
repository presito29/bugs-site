package com.plannerapp.service.task;

import com.plannerapp.model.TaskModelAdd;
import com.plannerapp.model.TaskViewModel;

import java.util.List;

public interface TaskService {
    void addTask(TaskModelAdd taskModelAdd);

    List<TaskViewModel> getAllNonLoggedUserTasks();

    void getTaskById(Long id);

    void deleteTaskById(Long id);
}
