package com.plannerapp.vallidation.usernameAndEmail;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = FieldExistenceValidator.class)
public @interface ValidateUnique {

    String message() default "User with this username or email already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}