package com.agenda.app.dto.payload;

import lombok.Data;

@Data
public class UsuarioEditPerfil {
    private String username;
    private String email;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String ciudad;
    private String pais;
    private String descripcion;
}
