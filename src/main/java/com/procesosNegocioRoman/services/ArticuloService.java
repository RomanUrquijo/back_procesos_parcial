package com.procesosNegocioRoman.procesos.services;

import com.procesosNegocioRoman.procesos.model.Articulo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArticuloService {
    ResponseEntity <Articulo> getArticleFindBycodige(Long codigo);
    ResponseEntity <List<Articulo>> allArticles();
    ResponseEntity <Articulo> createArticle( Articulo articulo);
    ResponseEntity <Articulo> editArticle(Long codigo, Articulo articulo);
    ResponseEntity <Articulo> deleteArticle(Long codigo);
}