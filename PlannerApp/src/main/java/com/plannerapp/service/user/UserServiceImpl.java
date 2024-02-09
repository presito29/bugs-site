package com.plannerapp.service.user;

import com.plannerapp.model.TaskViewModel;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.enums.PriorityTypeEnum;
import com.plannerapp.model.helpers.LoggedUser;
import com.plannerapp.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<TaskViewModel> allOwn() {
        User user = this.userRepository.findById(this.loggedUser.getId()).orElseThrow();
        return user.getTasks().stream().map(task -> taskBuilder(task, task.getUser().getUsername()))
                .collect(Collectors.toList());
    }

    @Override
    public TaskViewModel taskBuilder(Task task, String username) {
        return TaskViewModel.builder()
                .id(task.getId())
                .description(task.getDescription())
                .priority(PriorityTypeEnum.valueOf(task.getPriority().getName().string))
                .dueDate(task.getDueDate())
                .user(username)
                .build();
    }
}
