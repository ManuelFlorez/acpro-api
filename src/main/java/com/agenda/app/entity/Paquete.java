package com.agenda.app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "paquete")
@Entity
public class Paquete {
    @Id
    @Column(name = "paqu_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "paqu_nombre")
    private String nombre;
}
