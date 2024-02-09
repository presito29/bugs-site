package com.plannerapp.service.user;


import com.plannerapp.model.binding.UserLoginBindingModel;
import com.plannerapp.model.binding.UserRegisterBindingModel;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.helpers.LoggedUser;
import com.plannerapp.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository userRepository, LoggedUser loggedUser, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.encoder = encoder;
    }


    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRepository.saveAndFlush(User.builder()
                .username(userRegisterBindingModel.getUsername())
                .password(encoder.encode(userRegisterBindingModel.getPassword()))
                .email(userRegisterBindingModel.getEmail())
                .tasks(new ArrayList<>())
                .build());
    }

    @Override
    public boolean isMatch(String username, String password) {
        User user = this.userRepository.findByUsername(username).orElse(new User());
        String encodedPassword = user.getPassword();

        return encoder.matches(password, encodedPassword);
    }

    @Override
    public void loginUser(UserLoginBindingModel userLoginBindingModel) {
        User user = this.userRepository.findByUsername(userLoginBindingModel.getUsername()).orElse(new User());

        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public void logoutUser() {
        this.loggedUser.logout();
    }
}
