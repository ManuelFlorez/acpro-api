package com.agenda.app.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class UserDto {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String code;
    @NotNull
    private String password;
    private String rol;

    public boolean equals(UserDto user) {
        return user.getCode().equals(code) && user.getEmail().equals(email) && user.getPassword().equals(password);
    }
}
