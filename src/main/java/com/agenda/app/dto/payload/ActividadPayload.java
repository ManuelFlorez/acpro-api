package com.agenda.app.dto.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ActividadPayload {
    private int id;
    @NotNull(message = "Debe ingresar un id de tipo de actividad")
    private int tipoActividadId;
    @NotNull(message = "Debe ingresar un id del paquete")
    private int paqueteId;
    @NotNull(message = "Debe ingresar un id de tipo de responsable")
    private int tipoResponsableId;
    @NotEmpty(message = "Debe ingresar nombre de responsable")
    @NotNull(message = "Debe ingresar nombre de responsable")
    private String nombreResponsable;
    @NotEmpty(message = "Debe ingresar nombre de la actividad")
    @NotNull(message = "Debe ingresar nombre de la actividad")
    private String nombre;
    @NotEmpty(message = "Debe ingresar una descripción")
    @NotNull(message = "Debe ingresar una descripción")
    private String descripcion;
    @NotEmpty(message = "Debe ingresar una ciudad")
    @NotNull(message = "Debe ingresar una ciudad")
    private String ciudad;
    @NotEmpty(message = "Debe ingresar un pais")
    @NotNull(message = "Debe ingresar un pais")
    private String pais;
    @NotNull(message = "Debe ingresar numero de estudiantes")
    private int numeroEstudiantes;
    @NotNull(message = "Debe ingresar numero de docentes")
    private int numeroDocentes;
    @NotNull(message = "Debe ingresar numero de personas")
    private int numeroPersonas;
    @NotNull(message = "Debe ingresar numero de possonas administrativos")
    private int numeroPersonasAdministrativo;
    @NotNull(message = "Debe ingresar el id del usuario")
    private int usuarioId;
    @NotNull(message = "Debe ingresar una fecha de la actividad")
    private String fecha;
}
