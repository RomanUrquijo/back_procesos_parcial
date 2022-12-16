package com.procesosNegocioRoman.procesos;

import com.procesosNegocioRoman.procesos.model.Categoria;
import com.procesosNegocioRoman.procesos.repository.CategoriaRepository;
import com.procesosNegocioRoman.procesos.services.CategoriaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
//import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CategoriaServiceMockTest {
    public static Categoria mockCategoria() {
        Categoria modelo = new Categoria();
        modelo.setCodigo(1L);
        modelo.setNombre("Ropa");
        modelo.setDescripcion("Productos de tela");

        return modelo;
    }
    public static Categoria mockCategoriaMod() {
        Categoria modelo = new Categoria();
        modelo.setCodigo(1L);
        modelo.setNombre("Ropa");
        modelo.setDescripcion("Productos de algodon");

        return modelo;
    }

    @InjectMocks
    private CategoriaServiceImpl categoriaService;
    @Mock
    private CategoriaRepository categoriaRepository;


    @DisplayName("Test para listar a las categorias")
    @Test
    void gatAllCategoriaTest() {
        //Given
        Categoria categoria = mockCategoria();
        //When
        ResponseEntity<List<Categoria>> lista = categoriaService.allCategory();
        //Then
        Assertions.assertNotNull(lista);
    }
    @DisplayName("Test para crear Categoria")
    @Test
    void createArticleTest() {
        //Given
        Categoria categoria = mockCategoria();
        given(categoriaRepository.findById(categoria.getCodigo())).willReturn(Optional.of(categoria));
        given(categoriaRepository.save(categoria)).willReturn(categoria);
        //When

        ResponseEntity<Categoria> categoriaGuardado = categoriaService.createCategory(categoria);

        //Then
        Assertions.assertNotNull(categoriaGuardado);
    }
    @DisplayName("Test para editar una Categoria")
    @Test
    void editCategoriaTest() {
        // Given
        Categoria categoria = mockCategoria();
        Categoria categoriaMod = mockCategoriaMod();
        given(categoriaRepository.findById(categoria.getCodigo())).willReturn(Optional.of(categoria));
        given(categoriaRepository.save(categoriaMod)).willReturn(categoriaMod);

        //when

        ResponseEntity<Categoria> categoriaGuardado = categoriaService.editCategory(categoria.getCodigo(), categoriaMod);

        //Then
        Assertions.assertNotNull(categoriaGuardado);
    }

    @Test
    void whenNoEncuentraNingunaCategoria() {
        Categoria categoria = null;

        //When
        when(categoriaRepository.findAll()).thenReturn(Collections.emptyList());

        List<Categoria> categoria1 = categoriaService.allCategory().getBody();

        //Then
        Assertions.assertEquals(null, categoria1);

    }
}