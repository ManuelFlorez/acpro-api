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
    @JoinColumn(name = "usua_id")
    private User usuario;

    @OneToOne
    @JoinColumn(name = "tiac_id")
    private TipoActividad tipoActividad;

    @OneToOne
    @JoinColumn(name = "tire_id")
    private TipoResponsable tipoResponsable;

    @Column(name = "acti_nombre_responsable")
    private String nombreResponsable;

    @Column(name = "acti_descripcion")
    private String descripcion;

    @Column(name = "acti_ciudad")
    private String ciudad;

    @Column(name = "acti_pais")
    private String pais;

    @Column(name = "acti_numero_estudiantes")
    private Integer numeroEstudiantes;

    @Column(name = "acti_numero_docentes")
    private Integer numeroDocentes;

    @Column(name = "acti_numero_personas")
    private Integer numeroPersonas;

    @Column(name = "acti_numero_personal_administrativo")
    private Integer numeroPersonasAdministrativo;

}
