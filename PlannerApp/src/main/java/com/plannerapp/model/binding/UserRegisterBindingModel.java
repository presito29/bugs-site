package com.plannerapp.model.binding;



import com.plannerapp.vallidation.password.PasswordMatch;
import com.plannerapp.vallidation.usernameAndEmail.ValidateUnique;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@PasswordMatch
@ValidateUnique
public class UserRegisterBindingModel {



        @Size(min = 3, max = 20)
        @NotNull
        private String username;

        @Size(min = 3, max = 20)
        @NotNull
        private String password;

        @Size(min = 3, max = 20)
        @NotNull
        private String confirmPassword;

        @Email
        @NotEmpty
        private String email;
    }

