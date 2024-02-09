package com.plannerapp.controller;

import com.plannerapp.model.TaskViewModel;
import com.plannerapp.model.helpers.LoggedUser;
import com.plannerapp.service.task.TaskService;
import com.plannerapp.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final UserService userService;
    private final TaskService taskService;

    public HomeController(LoggedUser loggedUser, UserService userService, TaskService taskService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping
    public String getIndex() {
        return "index";
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView model) {

        List<TaskViewModel> loggedUserTask = this.userService.allOwn();
        List<TaskViewModel> otherTasks = this.taskService.getAllNonLoggedUserTasks();

        model.setViewName("home");

        model.addObject("loggedUserTask", loggedUserTask);
        model.addObject("otherTasks", otherTasks);


        model.addObject("loggedUserId", this.loggedUser.getId());

        return model;
    }

    @GetMapping("/tasks/get/{id}")
    public String getTask(@PathVariable Long id) {

        this.taskService.getTaskById(id);

        return "redirect:/home";
    }

    @GetMapping("/tasks/remove/{id}")
    public String readyTask(@PathVariable Long id) {

        this.taskService.deleteTaskById(id);
        return "redirect:/home";
    }
}
