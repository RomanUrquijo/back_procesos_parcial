package com.procesosNegocioRoman.procesos;

import com.procesosNegocioRoman.procesos.model.Usuario;
import com.procesosNegocioRoman.procesos.repository.UsuarioRepository;
import com.procesosNegocioRoman.procesos.services.UsuarioServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import static org.mockito.BDDMockito.given;
@ExtendWith(SpringExtension.class)
public class UserServiceMockTest {
    public static Usuario mockUser() {
        Usuario modelo = new Usuario();
        modelo.setId(1L);
        modelo.setNombre("Josue");
        modelo.setApellidos("Campo");
        modelo.setDocumento("1002334333");
        modelo.setDireccion("Ocaña");
        modelo.setFechaNacimiento(new Date(10,10,20));
        modelo.setTelefono("3143454323");
        modelo.setCorreo("josue@gmail.com");
        modelo.setPassword("ashwusudsu");
        return modelo;
    }
    public static Usuario mockUserMod() {
        Usuario modelo = new Usuario();
        modelo.setNombre("yeison");
        modelo.setApellidos("ascanio");
        modelo.setDocumento("1002894333");
        modelo.setDireccion("Ocaña");
        modelo.setFechaNacimiento(new Date(10,10,20));
        modelo.setTelefono("3123454323");
        modelo.setCorreo("yeison@gmail.com");
        modelo.setPassword("aureisfidsu");
        return modelo;
    }
    @InjectMocks
    private UsuarioServiceImpl userService;

    @Mock
    private UsuarioRepository userRepository;


    @DisplayName("Test para crear Usuario")
    @Test
    void createUserTest() {
        //Given
        Usuario user = mockUser();
        given(userRepository.findAllByNombre(user.getNombre())).willReturn(List.of(user));
        given(userRepository.findAllByApellidos(user.getApellidos())).willReturn(List.of(user));
        given(userRepository.findAllByNombreAndApellidos(user.getNombre(), user.getApellidos())).willReturn(List.of(user));
        given(userRepository.findByCorreo(user.getCorreo())).willReturn(user);
        given(userRepository.save(user)).willReturn(user);
        //When

        ResponseEntity<Usuario> articuloGuardado = userService.createUser(user);

        //Then
        Assertions.assertNotNull(articuloGuardado);
    }

    @DisplayName("Test para listar a los Usuarios")
    @Test
    void getAllUsersTest() {

        //Given
        Usuario user = mockUser();

        //When

        ResponseEntity<List<Usuario>> lista = userService.allUsers();

        //Then
        Assertions.assertNotNull(lista);
    }

    @DisplayName("Test para obtener usuarios por codigo")
    @Test
    void GetArticleByCodTest() {

        //Given
        Usuario user = mockUser();

        //When
        given(userRepository.findAllByNombre(user.getNombre())).willReturn(List.of(user));
        given(userRepository.findAllByApellidos(user.getApellidos())).willReturn(List.of(user));
        given(userRepository.findAllByNombreAndApellidos(user.getNombre(), user.getApellidos())).willReturn(List.of(user));
        given(userRepository.findByCorreo(user.getCorreo())).willReturn(user);
        ResponseEntity<Usuario> respuesta = userService.getUserById(user.getId());

        //Then
        Assertions.assertNotNull(respuesta);

    }

}