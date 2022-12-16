package com.procesosNegocioRoman.procesos.services;

import com.procesosNegocioRoman.procesos.model.Usuario;
import com.procesosNegocioRoman.procesos.repository.UsuarioRepository;
import com.procesosNegocioRoman.procesos.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl  implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<Usuario> getUserById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent())
        {
            return new ResponseEntity(usuario, HttpStatus.OK);
        }
        return  ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Usuario> createUser(Usuario usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioRepository.save(usuario);
            return new ResponseEntity(usuario, HttpStatus.CREATED);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Usuario>> allUsers() {
        List<Usuario> usuarios =usuarioRepository.findAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  new ResponseEntity(usuarios, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Usuario>> allUsersByNameAndLastName(String nombre, String apellidos) {
        List<Usuario> usuarios= usuarioRepository.findAllByNombreAndApellidos(nombre,apellidos);;
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  new ResponseEntity(usuarios, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Usuario>> allUsersByName(String nombre) {
        List<Usuario> usuarios= usuarioRepository.findAllByNombre(nombre);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  new ResponseEntity(usuarios, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Usuario>> allUsersByLastName(String apellidos) {
        List<Usuario> usuarios= usuarioRepository.findAllByApellidos(apellidos);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  new ResponseEntity(usuarios, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Usuario> editUser(Long id, Usuario usuario) {
        Optional<Usuario> usuarioBD =usuarioRepository.findById(id);
        if(usuarioBD.isPresent()){
            try{
                usuarioBD.get().setNombre(usuario.getNombre());
                usuarioBD.get().setApellidos(usuario.getApellidos());
                usuarioBD.get().setDireccion(usuario.getDireccion());
                usuarioBD.get().setDocumento(usuario.getDocumento());
                usuarioBD.get().setTelefono(usuario.getTelefono());
                usuarioBD.get().setFechaNacimiento(usuario.getFechaNacimiento());
                usuarioBD.get().setCorreo(usuario.getCorreo());
                usuarioBD.get().setPassword(passwordEncoder.encode(usuario.getPassword()));
                usuarioRepository.save(usuarioBD.get());
                return new ResponseEntity(usuarioBD,HttpStatus.OK);
            }catch (Exception e){
                return  ResponseEntity.badRequest().build();

            }
        }
        return  ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Usuario> deleteUser(Long id) {
        Optional<Usuario> usuarioBD =usuarioRepository.findById(id);
        if(usuarioBD.isPresent()){
            usuarioRepository.delete(usuarioBD.get());
            return  ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity login(String correo, String password) {
        try{
            Usuario usuario = usuarioRepository.findByCorreo(correo);
            if(passwordEncoder.matches(password,usuario.getPassword())){
                String token = jwtUtil.create(String.valueOf(usuario.getId()), usuario.getCorreo());
                return ResponseEntity.ok(token);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity getUserByCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if(usuario!= null){
            return new ResponseEntity(usuario, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}
