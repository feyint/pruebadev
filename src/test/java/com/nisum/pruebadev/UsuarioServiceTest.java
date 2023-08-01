/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nisum.pruebadev;

import com.nisum.pruebadev.entity.Usuario;
import com.nisum.pruebadev.exception.EmailAlreadyRegisteredException;
import com.nisum.pruebadev.repository.UsuarioRepository;
import com.nisum.pruebadev.service.UsuarioService;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author feyint
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class UsuarioServiceTest {
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testCreateUser() {
        // Crea un usuario para la prueba
        Usuario usuario = new Usuario();
        usuario.setEmail("eduar@gmail.com");
        usuario.setName("Eduar");

        // Llama al método que se va a probar
        Usuario usuarioCreado = usuarioService.createUser(usuario);

        // Verifica que el usuario se haya creado correctamente
        assertNotNull(usuarioCreado.getId());
        assertEquals("Eduar", usuarioCreado.getName());
        assertEquals("eduar@gmail.com", usuarioCreado.getEmail());
    }
    
    
    @Test
    public void testCreateUserWithEmailDuplicado() {
        // Crea un usuario y guárdalo en la base de datos
        Usuario usuario1 = new Usuario();
        usuario1.setEmail("eduar@gmail.com");
        usuario1.setName("Eduar");
        usuarioRepository.save(usuario1);

        // Intenta crear otro usuario con el mismo correo electrónico
        Usuario usuarioAux = new Usuario();
        usuarioAux.setEmail("eduar@gmail.com");
        usuarioAux.setName("Eduardo");

        // Verifica que se lance la excepción adecuada
        assertThrows(EmailAlreadyRegisteredException.class, () -> usuarioService.createUser(usuarioAux));
    }
}
