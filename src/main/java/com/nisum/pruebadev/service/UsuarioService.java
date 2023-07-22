/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.nisum.pruebadev.service;

import com.nisum.pruebadev.entity.Usuario;
import com.nisum.pruebadev.exception.EmailAlreadyRegisteredException;
import com.nisum.pruebadev.repository.UsuarioRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author feyint
 */
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario createUser(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException("El correo ya registrado");
        }

        usuario.setCreated(LocalDateTime.now().now());
        usuario.setLastLogin(LocalDateTime.now());
        usuario.setToken(UUID.randomUUID().toString());
        usuario.setIsActive(true);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }
}
