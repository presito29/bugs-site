package com.plannerapp.vallidation.login;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = LoginValidator.class)
public @interface Login {


        String message() default "User already exists";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
}
