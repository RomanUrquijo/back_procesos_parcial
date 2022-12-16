package com.procesosNegocioRoman.procesos.controller;

import com.procesosNegocioRoman.procesos.model.Categoria;
import com.procesosNegocioRoman.procesos.services.CategoriaService;
import com.procesosNegocioRoman.procesos.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(value = "/categoria")
    public ResponseEntity crearCategoria(@RequestBody Categoria categoria, @RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                return categoriaService.createCategory(categoria);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
    }

    @GetMapping(value = "/categoria/{codigo}")
    public ResponseEntity getCategoria(@PathVariable Long codigo,@RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                return categoriaService.getCategoryFindBycodige(codigo);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }

    }


    @PutMapping("/categoria/{codigo}")
    public ResponseEntity editarCategoria(@PathVariable Long codigo, @RequestBody Categoria categoria,@RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                return categoriaService.editCategory(codigo,categoria);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
    }


    @GetMapping(value = "/categorias")
    public ResponseEntity listarCategorias(@RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                return categoriaService.allCategory();
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
    }




    @DeleteMapping("/categoria/{codigo}")
    public ResponseEntity eliminarCategoria(@PathVariable Long codigo,@RequestHeader(value="Authorization") String token) {
        try {
            if (jwtUtil.getKey(token) != null) {
                return categoriaService.deleteCategory(codigo);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
