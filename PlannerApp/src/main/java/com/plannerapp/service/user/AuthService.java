package com.plannerapp.service.user;


import com.plannerapp.model.binding.UserLoginBindingModel;
import com.plannerapp.model.binding.UserRegisterBindingModel;

public interface AuthService {
    void registerUser(UserRegisterBindingModel userRegisterBindingModel);

    boolean isMatch(String username, String password);

    void loginUser(UserLoginBindingModel userLoginBindingModel);

    void logoutUser();
}

