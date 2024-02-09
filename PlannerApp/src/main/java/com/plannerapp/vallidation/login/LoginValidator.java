package com.plannerapp.vallidation.login;


import com.plannerapp.model.binding.UserLoginBindingModel;
import com.plannerapp.model.entity.User;
import com.plannerapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class LoginValidator implements ConstraintValidator<Login, UserLoginBindingModel> {

    private final UserService userService;
    private final PasswordEncoder encoder;

    @Autowired
    public LoginValidator(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public void initialize(Login constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginBindingModel userLoginBindingModel, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> optionalUser = userService.findByUsername(userLoginBindingModel.getUsername());

        if (optionalUser.isEmpty()){
            return false;
        }

        String  rawPassword = userLoginBindingModel.getPassword();
        String  encodedPassword  = optionalUser.get().getPassword();

        return encoder.matches(rawPassword, encodedPassword);
    }
}
