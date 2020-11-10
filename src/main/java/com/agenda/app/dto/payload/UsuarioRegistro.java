package com.agenda.app.dto.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UsuarioRegistro {
    @NotNull
    @NotEmpty(message = "Debe ingresar un email")
    @Email(message = "Debe ingresar un email")
    private String correo;
    @NotNull(message = "Debe ingresar una clave")
    @NotEmpty(message = "Debe ingresar una clave")
    private String clave;
    @NotNull(message = "Debe ingresar un tipo de usuario")
    @NotEmpty(message = "Debe ingresar un tipo de usuario")
    private String tipoUsuario;
}
