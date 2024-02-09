package com.plannerapp.vallidation.usernameAndEmail;


import com.plannerapp.model.binding.UserRegisterBindingModel;
import com.plannerapp.model.entity.User;
import com.plannerapp.service.user.UserService;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class FieldExistenceValidator implements ConstraintValidator<ValidateUnique, UserRegisterBindingModel> {
    private final UserService userService;

    public FieldExistenceValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void initialize(ValidateUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterBindingModel userRegisterBindingModel, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> optionalUser = userService.findByUsername(userRegisterBindingModel.getUsername());

        if (optionalUser.isPresent()){
            return false;
        }

        Optional<User> optionalUserEmailCheck = userService.findByEmail(userRegisterBindingModel.getEmail());

        return optionalUserEmailCheck.isEmpty();

    }
    }

