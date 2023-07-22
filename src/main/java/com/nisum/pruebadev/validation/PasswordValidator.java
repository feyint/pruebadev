/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nisum.pruebadev.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author feyint
 */
@Component
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Value("${app.validation.password}")
    private String passwordPattern;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        // Aquí puedes hacer cualquier inicialización necesaria
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Devuelve true si el valor es null o coincide con la expresión regular
        return value == null || value.matches(passwordPattern);
    }
}