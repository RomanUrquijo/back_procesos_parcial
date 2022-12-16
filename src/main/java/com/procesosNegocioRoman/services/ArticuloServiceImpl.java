package com.procesosNegocioRoman.procesos.services;

import com.procesosNegocioRoman.procesos.model.Articulo;
import com.procesosNegocioRoman.procesos.repository.ArticuloRepository;
import com.procesosNegocioRoman.procesos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticuloService{

    @Autowired
    private ArticuloRepository articuloRepository;
    private CategoriaRepository categoriaRepository;

    @Override
    public ResponseEntity<Articulo> getArticleFindBycodige(Long codigo) {
        Optional<Articulo> articulo= articuloRepository.findById(codigo);
        if(articulo.isPresent()){
            return new ResponseEntity(articulo, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Articulo>> allArticles() {
        List<Articulo> articulos = articuloRepository.findAll();
        if (articulos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(articulos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Articulo> createArticle(Articulo articulo) {
        try {
            articuloRepository.save(articulo);
            return new ResponseEntity(articulo,HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Articulo> editArticle(Long codigo, Articulo articulo) {
        Optional<Articulo> articuloBD = articuloRepository.findById(codigo);
        if (articuloBD.isPresent()){
            try {
                articuloBD.get().setNombre(articulo.getNombre());
                articuloBD.get().setDescripcion(articulo.getDescripcion());
                articuloBD.get().setFecha_registro(articulo.getFecha_registro());
                articuloBD.get().setCategoria(articulo.getCategoria());
                articuloBD.get().setPrecio_compra(articulo.getPrecio_compra());
                articuloBD.get().setPrecio_venta(articulo.getPrecio_venta());
                articuloRepository.save(articuloBD.get());
                return new ResponseEntity(articuloBD,HttpStatus.OK);
            }catch (Exception e){
                System.out.println(e.fillInStackTrace());
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Articulo> deleteArticle(Long codigo) {
        Optional<Articulo> articuloBD = articuloRepository.findById(codigo);
        if (articuloBD.isPresent()){
            articuloRepository.delete(articuloBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}