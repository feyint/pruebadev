/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nisum.pruebadev.repository;

import com.nisum.pruebadev.entity.Usuario;
import java.util.Optional;
//import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author feyint
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
