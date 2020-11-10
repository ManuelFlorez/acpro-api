package com.agenda.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Table(name = "usuarios")
@Entity
public class User {

    @Id
    @Column(name = "usua_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "usua_correo")
    private String correo;

    @Column(name = "usua_codigo")
    private String codigo;

    @Column(name = "usua_clave")
    private String clave;

    @Column(name = "usua_username")
    private String username;

    @Column(name = "usua_nombres")
    private String nombres;

    @Column(name = "usua_apellidos")
    private String apellidos;

    @Column(name = "usua_direccion")
    private String direccion;

    @Column(name = "usua_ciudad")
    private String ciudad;

    @Column(name = "usua_pais")
    private String pais;

    @Column(name = "usua_descripcion")
    private String descripcion;

    @Column(name = "usua_rol")
    private String rol;

    @Column(name = "usua_reg_date")
    private Timestamp fechaIngreso;

    @Column(name = "usua_estado")
    private String estado;
}
