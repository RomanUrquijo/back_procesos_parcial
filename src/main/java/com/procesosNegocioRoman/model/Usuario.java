package com.procesosNegocioRoman.procesos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( nullable = false, length = 100)
    @NotBlank(message = "el nombre no puede ser nulo")
    private String nombre;
    @Column( nullable = false, length = 300)
    @NotBlank(message = "los apellidos no pueden estar vacios")
    private String apellidos;
    @Column( nullable = false, length = 20, unique = true)
    @NotBlank(message = "el documento no puede ser nulo")
    private String documento;
    @Column( length = 100)
    private String direccion;
    private Date fechaNacimiento;
    @Column( length = 20)
    private String telefono;
    @Column(unique = true,nullable = false,length = 100)
    @NotBlank(message = "el correo no puede estar vacio")
    private String correo;
    @Column(nullable = false,length = 64)
    @NotBlank(message = "la contraseña no puede estar vacia")
    private String password;
}