package com.nisum.pruebadev.controller;

import com.nisum.pruebadev.entity.ErrorResponse;
import com.nisum.pruebadev.entity.Usuario;
import com.nisum.pruebadev.exception.EmailAlreadyRegisteredException;
import com.nisum.pruebadev.exception.InvalidFormatException;
import com.nisum.pruebadev.service.UsuarioService;
import com.nisum.pruebadev.service.ValidationService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
@Api(value = "Gestión de usuarios", description = "Operaciones para gestionar usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ValidationService validationService;
    
    @Operation(summary = "Crear nuevo usuario", responses = {
        @ApiResponse(responseCode = "200", description = "Usuario creado con éxito",
                content = @Content(schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "400", description = "Error, email ya registrado",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/usuarios")
    public ResponseEntity<?> createUser(@Valid @RequestBody Usuario usuario) {        
        try {
            validationService.validateUsuario(usuario);
            Usuario nuevoUsuario = usuarioService.createUser(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (InvalidFormatException | EmailAlreadyRegisteredException ex) {
            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> usuarios = usuarioService.getAllUsers();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(usuarios);
        }
    }
    
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUser(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
}
