/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import aplicacion.modelo.Order;
import aplicacion.modelo.OrderDetails;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Cole
 */
public class OrderDetailsLogic extends LogicLayer{

    public OrderDetailsLogic() throws LogicLayerException {
        super();
    }

    /**
     * Obtiene todos los pedidos de un cliente.
     * @param order Objeto de tipo order.
     * @return orders Lista de ordenes.
     */
    public List<OrderDetails> getAllByOrderNumber(Order order) throws LogicLayerException{        
        try {
            return this.getOrderDetailsDAO().getAllByOrderNumber(order);
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa lógica: " + ex.getMessage());
        }
    }
    
    /**
     * Guarda un detalle de pedido en la base de datos
     * @param order Objeto de tipo orderDetails.
     */
    public void save(OrderDetails orderDetail) throws LogicLayerException {
        try {
            this.getOrderDetailsDAO().save(orderDetail);
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa lógica: " + ex.getMessage());
        }
    }

    /**
     * Actualiza un detalle de pedido en la base de datos basado en el objeto del argumento.
     * @param order Objeto de tipo orderDetails.
     */
    public void update(OrderDetails orderDetails) throws LogicLayerException {
        try {
            this.getOrderDetailsDAO().update(orderDetails);
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa lógica: " + ex.getMessage());
        }
    }

     /**
     * Elimina un pedido en la base de datos basado en el objeto del argumento.
     * @param order Objeto de tipo order.
     */
    public void delete(OrderDetails order) throws LogicLayerException {
        try {
            this.getOrderDetailsDAO().delete(order);
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa lógica: " + ex.getMessage());
        }
    }
    
    @Override
    public void close() throws LogicLayerException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
