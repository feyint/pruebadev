/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.nisum.pruebadev;

import com.nisum.pruebadev.controller.UsuarioController;
import com.nisum.pruebadev.entity.Usuario;
import com.nisum.pruebadev.exception.EmailAlreadyRegisteredException;
import com.nisum.pruebadev.service.UsuarioService;
import com.nisum.pruebadev.service.ValidationService;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author feyint
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {
    
    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;
    
    @Mock
    private ValidationService validationService;
    
    @Test
    public void testCreateUserSuccess() {
        Usuario usuario = new Usuario();
        usuario.setEmail("eduar@gmail.com");
        usuario.setPassword("Eduar123");
        when(usuarioService.createUser(any(Usuario.class))).thenReturn(usuario);
        ResponseEntity<?> response = usuarioController.createUser(usuario);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
        verify(usuarioService, times(1)).createUser(any(Usuario.class));
    }

    
    
    @Test
    public void testCreateUserEmailAlreadyRegistered() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@test.com");
        usuario.setPassword("Cl@ve123");

        when(usuarioService.createUser((Usuario) any(Usuario.class))).thenThrow(new EmailAlreadyRegisteredException("Email ya registrado"));

        ResponseEntity<?> response = usuarioController.createUser(usuario);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        verify(usuarioService, times(1)).createUser(usuario);
    }
    
}
