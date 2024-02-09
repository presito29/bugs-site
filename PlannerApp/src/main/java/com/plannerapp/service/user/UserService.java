package com.plannerapp.service.user;

import com.plannerapp.model.TaskViewModel;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<TaskViewModel> allOwn();


    TaskViewModel taskBuilder(Task task, String username);
}
