package com.plannerapp.service.task;

import com.plannerapp.model.TaskModelAdd;
import com.plannerapp.model.TaskViewModel;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.enums.PriorityTypeEnum;
import com.plannerapp.model.helpers.LoggedUser;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskServiceImpl implements TaskService{
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;
    @Autowired
    public TaskServiceImpl(UserRepository userRepository, LoggedUser loggedUser, TaskRepository taskRepository, PriorityRepository priorityRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void addTask(TaskModelAdd taskModelAdd) {

        User currentUser = this.userRepository.findById(this.loggedUser.getId()).orElseThrow();
        Task task = this.taskRepository.saveAndFlush(Task.builder()
                .description(taskModelAdd.getDescription())
                .priority(this.priorityRepository.findByName(taskModelAdd.getPriority()))
                        .dueDate(taskModelAdd.getDueDate())
                .build());

        currentUser.getTasks().add(task);
    }

    @Override
    public List<TaskViewModel> getAllNonLoggedUserTasks() {
        return this.taskRepository.findAllByUser_IdNot(this.loggedUser.getId()).orElseThrow(NoSuchElementException::new).stream()
                .map(this::buildViewModel).toList();
    }

    @Override
    public void getTaskById(Long id) {
        Task task = this.taskRepository.findById(id).orElseThrow();
        User user = this.userRepository.findById(this.loggedUser.getId()).orElseThrow();
        User logoutUser = task.getUser();

        task.setUser(user);
        user.getTasks().add(task);
        logoutUser.getTasks().remove(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskViewModel buildViewModel(Task task) {
        return TaskViewModel.builder()
                .id(task.getId())
                .user(task.getUser().getUsername())
                .description(task.getDescription())
                .priority(PriorityTypeEnum.valueOf(task.getPriority().getName().string))
                .dueDate(task.getDueDate())
                .build();
    }
}
