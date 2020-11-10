package com.agenda.app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tipo_responsable")
@Entity
public class TipoResponsable {
    @Id
    @Column(name = "tire_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tire_nombre")
    private String nombre;
}
