package com.plannerapp.vallidation.password;

import com.plannerapp.model.binding.UserRegisterBindingModel;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordMatcherRegister implements ConstraintValidator<PasswordMatch, UserRegisterBindingModel> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterBindingModel userRegisterBindingModel, ConstraintValidatorContext constraintValidatorContext) {
        return userRegisterBindingModel.getPassword() != null && userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());
    }
}
