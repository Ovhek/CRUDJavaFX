/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 * Clase de excepciones personalizada para la capa lógica.
 */
public class LogicLayerException extends Exception {
    
    /**
     * Constructor sin parámetros de la clase.
     */
    public LogicLayerException() {
    }

    /**
     * Constructor que recibe un mensaje de error.
     *
     * @param message Mensaje de error.
     */
    public LogicLayerException(String message) {
        super(message);
    }

    /**
     * Constructor que recibe un mensaje de error y la excepción original que ha ocurrido.
     *
     * @param message Mensaje de error.
     * @param cause Excepción original que ha ocurrido.
     */
    public LogicLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
