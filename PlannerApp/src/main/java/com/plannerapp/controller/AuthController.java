package com.plannerapp.controller;

import com.plannerapp.model.binding.UserLoginBindingModel;
import com.plannerapp.model.binding.UserRegisterBindingModel;
import com.plannerapp.service.user.AuthService;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute(name = "userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        this.authService.registerUser(userRegisterBindingModel);
        return "redirect:login";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid @ModelAttribute(name = "userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        }

        if(this.authService.isMatch(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword())){
            this.authService.loginUser(userLoginBindingModel);

            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:login";
        }
    }

    @GetMapping("/logout")
    public String logoutUser(){
        this.authService.logoutUser();
        return "index";
    }



    @ModelAttribute(name="userRegisterBindingModel")
    private UserRegisterBindingModel userRegisterModel(){
        return new UserRegisterBindingModel();
    }
    @ModelAttribute(name = "userLoginBindingModel")
    public UserLoginBindingModel userLoginModel() {
        return new UserLoginBindingModel();
    }

}

