package com.procesosNegocioRoman.procesos.repository;


import com.procesosNegocioRoman.procesos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List <Categoria> findAllByCodigo (Long codigo);
}
