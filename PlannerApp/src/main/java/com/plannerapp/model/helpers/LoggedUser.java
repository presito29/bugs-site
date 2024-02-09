package com.plannerapp.model.helpers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoggedUser {
    private Long id;
    private String username;

    public boolean isEmpty() {
        return this.id == null;
    }

    public void logout() {
        this.id = null;
    }
}
