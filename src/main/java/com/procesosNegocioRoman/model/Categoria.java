package com.procesosNegocioRoman.procesos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(length = 100,nullable = false)
    private String nombre;

    @Column(length = 300,nullable = false)
    private String descripcion;

}