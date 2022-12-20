/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 * Clase que representa una excepción con la edad del cliente.
 * 
 */
public class CustomerAgeException extends Exception {

    /**
     * Constructor sin argumentos.
     */
    public CustomerAgeException() {
    }

    /**
     * Constructor que recibe un mensaje de error.
     *
     * @param message Mensaje de error.
     */
    public CustomerAgeException(String message) {
        super(message);
    }

    /**
     * Constructor que recibe un mensaje de error y la causa de la excepción.
     *
     * @param message Mensaje de error.
     * @param cause Causa de la excepción.
     */
    public CustomerAgeException(String message, Throwable cause) {
        super(message, cause);
    }
}
