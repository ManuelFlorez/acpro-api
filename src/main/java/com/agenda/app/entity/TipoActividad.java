package com.agenda.app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tipo_actividad")
@Entity
public class TipoActividad {
    @Id
    @Column(name = "tiac_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tiac_nombre")
    private String nombre;
}
