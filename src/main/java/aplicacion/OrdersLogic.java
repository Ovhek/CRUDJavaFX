/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import aplicacion.modelo.Customer;
import aplicacion.modelo.Order;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Cole
 */
public class OrdersLogic extends LogicLayer{

    public OrdersLogic() throws LogicLayerException {
        super();
    }

     /**
     * Obtiene todos los pedidos de un cliente del DAO
     * @param order Objeto de tipo order.
     * @return orders Lista de ordenes.
     * @throw excepción cuando ocurre un error de recuperación de datos.
     */
    public List<Order> getAllByCustomer(Customer customer) throws LogicLayerException{        
        try {
            return this.getOrdersDAO().getAllByCustomer(customer);
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa logica: " + ex.getMessage());
        }
    }
    
        
    /**
     * Guarda un pedido en la base de datos
     * @param order Objeto de tipo order.
     * @throw excepción cuando ocurre un error de al guardar un pedido en la base de datos
     */
    public void save(Order order) throws LogicLayerException{
        try {
            this.getOrdersDAO().save(order);
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa logica: " + ex.getMessage());
        }
    }

    /**
     * Actualiza un pedido en la base de datos basado en el objeto del argumento.
     * @param order Objeto de tipo order.
     * @throw excepción cuando ocurre un error de al actualizar un pedido en la base de datos
     */
    public void update(Order order) throws LogicLayerException {
        try {
            this.getOrdersDAO().update(order);
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa logica: " + ex.getMessage());
        }

    }

     /**
     * Elimina un pedido en la base de datos basado en el objeto del argumento.
     * @param order Objeto de tipo order.
     * @throw excepción cuando ocurre un error de al actualizar un pedido en la base de datos
     */
    public void delete(Order order) throws LogicLayerException {
        try {
            this.getOrdersDAO().delete(order);
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa logica: " + ex.getMessage());
        }
    }

    
    // Cerramos la conexión
    @Override
    public void close() throws LogicLayerException {
        try {
            this.getAppConfigDAO().close();
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa logica: " + ex.getMessage());
        }
    }
    
}
