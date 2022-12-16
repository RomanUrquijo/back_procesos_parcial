package com.procesosNegocioRoman.procesos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "articulos")
public class Articulo {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(length = 100)
    private String nombre;

    @Column(length = 300)
    private String descripcion;

    private Date fecha_registro;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Usuario usuario;

    @Column(length = 300)
    private String precio_venta;

    @Column(length = 300)
    private String precio_compra;

}
