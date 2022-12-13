/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz para implementar el DAO
 * @param <T> Parametro Generico
 */
public interface DAOInterface<T> {
     /***
     * Devuelve todos los valores de una tabla
     * @return valores de una tabla
     * @throws java.sql.SQLException 
     */
    public List<T> getAll() throws SQLException;
    
    /***
     * Guarda un elemento en una tabla
     * @param t elemento
     * @throws java.sql.SQLException si l'element ja existeix
     */
    public void save(T t) throws SQLException;
    
    /***
     * Actualiza un elemento de una tabla por su id
     * El elemento tiene que existir
     * @param t 
     * @throws java.sql.SQLException si l'element no existeix
     */
    public void update(T t) throws SQLException;
    
     /***
     * Elimina un elemento de una tabla por su id
     * El elemento ha de existir
     * @param t 
     * @throws java.sql.SQLException si l'element no existeix
     */
    public void delete(T t) throws SQLException;
    
    /***
     * Recupera un elemento de una tabla por su id
     * @param t
     * @return
     * @throws SQLException 
     */
    public T get(T t) throws SQLException;
}
