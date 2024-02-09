package com.plannerapp.controller;

import com.plannerapp.model.TaskModelAdd;
import com.plannerapp.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService offerService) {
        this.taskService = offerService;
    }


    @GetMapping("/add")
    public String getAddProduct(){
        return "task-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute(name= "taskModelAdd") TaskModelAdd taskModelAdd,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("taskModelAdd", taskModelAdd)
                    .addFlashAttribute("org.springframework.validation.BindingResult.taskModelAdd", bindingResult);

            return "redirect:add";
        }
        this.taskService.addTask(taskModelAdd);

        return "redirect:/home";
    }

    @ModelAttribute(name="taskModelAdd")
    public TaskModelAdd productAddModel(){
        return new TaskModelAdd();
    }

}

