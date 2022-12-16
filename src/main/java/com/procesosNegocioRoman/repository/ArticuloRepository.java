package com.procesosNegocioRoman.procesos.repository;

import com.procesosNegocioRoman.procesos.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    List <Articulo> findAllByCodigo (Long codigo);

    Object findByCodigo(long anyLong);
}
