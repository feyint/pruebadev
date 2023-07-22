/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nisum.pruebadev.service;

import com.nisum.pruebadev.entity.Usuario;
import com.nisum.pruebadev.exception.InvalidFormatException;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author feyint
 */
@Service
public class ValidationService {

   // private static final String EMAIL_PATTERN = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$";
   // private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    
    private final String exprEmail;
    private final String exprPassword;

    
    public ValidationService(@Value("${app.validation.email}") String exprEmail, @Value("${app.validation.password}") String exprPassword) {
        this.exprEmail = exprEmail;
        this.exprPassword = exprPassword;
    }
    
    
    
    

    public void validateUsuario(Usuario usuario) {
        if (!isValidEmail(usuario.getEmail())) {
            throw new InvalidFormatException("Email no es válido");
        }

        if (!isValidPassword(usuario.getPassword())) {
            throw new InvalidFormatException("Password no es válido");
        }
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches(exprEmail, email);
    }

    private boolean isValidPassword(String password) {
        return Pattern.matches(exprPassword, password);
    }
}
