package com.agenda.app.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "actividad")
@Entity
public class Actividad {
    @Id
    @Column(name = "acti_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "tiac_id")
    private TipoActividad tipoActividad;

    @OneToOne
    @JoinColumn(name = "tire_id")
    private TipoResponsable tipoResponsable;

    @JoinColumn(name = "acti_nombre_responsable")
    private String nombreResponsable;

    @JoinColumn(name = "acti_descripcion")
    private String descripcion;

    @JoinColumn(name = "acti_ciudad")
    private String ciudad;

    @JoinColumn(name = "acti_pais")
    private String pais;

    @JoinColumn(name = "acti_numero_estudiantes")
    private String numeroEstudiantes;

    @JoinColumn(name = "acti_numero_docentes")
    private String numeroDocentes;

    @JoinColumn(name = "acti_numero_personas")
    private String numeroPersonas;

    @JoinColumn(name = "acti_numero_personal_administrativo")
    private String numeroPersonasAdministrativo;

}
