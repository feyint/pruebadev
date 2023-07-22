/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.nisum.pruebadev.exception;

import org.springframework.stereotype.Service;

/**
 *
 * @author feyint
 */
public class EmailAlreadyRegisteredException extends RuntimeException{
     public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
