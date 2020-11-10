package com.agenda.app.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
public class UserDto {
    @NotEmpty(message = "Debe ingresar un email")
    @Email(message = "debe ingresar un email que sea valido")
    private String email;
    @NotEmpty(message = "debe ingresar una password")
    private String password;
    private String rol;

    public boolean equals(UserDto user) {
        return user.getEmail().equals(email) && user.getPassword().equals(password);
    }
}
