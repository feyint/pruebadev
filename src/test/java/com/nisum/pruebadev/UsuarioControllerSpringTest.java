/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nisum.pruebadev;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.pruebadev.entity.Usuario;
import com.nisum.pruebadev.exception.InvalidFormatException;
import com.nisum.pruebadev.service.UsuarioService;
import com.nisum.pruebadev.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author feyint
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerSpringTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UsuarioService usuarioService;
    
    @MockBean
    private ValidationService validationService;
      
    @Autowired
    private ObjectMapper objectMapper;
    
    
    @Test
    public void testCreateUser_ValidUsuario_ReturnsOk() throws Exception {

        
        //crear un dato de prueba para el objeto usuario
        Usuario usuario = new Usuario();
        usuario.setName("eduar");
        usuario.setEmail("eduar@gmail.com");
        usuario.setPassword("Eduar123");
               
        // Simular el servicio que valida los datos ingresados
        Mockito.doNothing().when(validationService).validateUsuario(Mockito.any());
        
        // Simular la creación de usuario
        Usuario usuarioCreado = new Usuario();
        usuarioCreado.setId(1L);
        usuarioCreado.setName("eduar");
        usuarioCreado.setEmail("eduar@gmail.com");
        Mockito.when(usuarioService.createUser(Mockito.any())).thenReturn(usuarioCreado);

        // Realizar la solicitud al metodo enviando el POST como json
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("eduar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("eduar@gmail.com"));

        // Verificar que se llame a los métodos correspondientes de los servicios
        Mockito.verify(validationService, Mockito.times(1)).validateUsuario(Mockito.any());
        Mockito.verify(usuarioService, Mockito.times(1)).createUser(Mockito.any());
        Mockito.verifyNoMoreInteractions(usuarioService);
    }    
}
