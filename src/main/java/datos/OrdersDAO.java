/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import aplicacion.modelo.Customer;
import aplicacion.modelo.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clse encargada del los comandos DAO de las ordenes
 * @author Cole
 */
public class OrdersDAO extends DataLayer implements DAOInterface<Order>{

    public OrdersDAO() throws SQLException {
        super();
    }

    /**
     * No implementable, es necesario el email del cliente para poder obtener las ordenes de forma lógica. Utilizar getAllByCustomer
     */
    @Override
    public List<Order> getAll() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        
        String consulta = "select * from orders";
        ResultSet rs = this.getCon().createStatement().executeQuery(consulta);
        
        while(rs.next()){
            orders.add(new Order(
                    rs.getInt("orderNumber"),
                    rs.getTimestamp("orderDate"),
                    rs.getTimestamp("requiredDate"),
                    rs.getTimestamp("shippedDate"),
                    rs.getString("customers_customerEmail")
            ));
        }
        return orders;
    }

    /**
     * Obtiene todos los pedidos de un cliente.
     * @param customer Objeto de tipo customer.
     * @return orders Lista de ordenes.
     */
    public List<Order> getAllByCustomer(Customer customer) throws SQLException{
        ArrayList<Order> orders = new ArrayList<>();
        
        String consulta = "select * from orders where customers_customerEmail = '"+customer.getCustomerEmail()+"'";
        ResultSet rs = this.getCon().createStatement().executeQuery(consulta);
        
        while(rs.next()){
            orders.add(new Order(
                    rs.getInt("orderNumber"),
                    rs.getTimestamp("orderDate"),
                    rs.getTimestamp("requiredDate"),
                    rs.getTimestamp("shippedDate"),
                    rs.getString("customers_customerEmail")
            ));
        }
        return orders;
    }
    
    /**
     * Guarda un pedido en la base de datos
     * @param order Objeto de tipo order.
     */
    @Override
    public void save(Order order) throws SQLException {
        Statement sentencia;
        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        sentencia.executeQuery("SELECT * FROM orders");
        ResultSet rs = sentencia.getResultSet();
        int orderNumber = 0;
        if(rs.next()){
            rs.last();
            orderNumber = rs.getInt("orderNumber")+1;
            if(orderNumber < order.getOrderNumber()) orderNumber = order.getOrderNumber();
        }
        rs.moveToInsertRow();
        rs.updateInt("orderNumber", orderNumber);
        rs.updateTimestamp("orderDate", order.getOrderDate());
        rs.updateTimestamp("requiredDate", order.getRequiredDate());
        rs.updateTimestamp("shippedDate", order.getShippedDate());
        rs.updateString("customers_customerEmail", order.getCustomers_customerEmail());
        
        rs.insertRow();

    }

    /**
     * Actualiza un pedido en la base de datos basado en el objeto del argumento.
     * @param order Objeto de tipo order.
     */
    @Override
    public void update(Order order) throws SQLException {
        Statement sentencia;
        
        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        sentencia.executeQuery("SELECT * FROM orders where orderNumber = " + order.getOrderNumber());
        ResultSet rs = sentencia.getResultSet();
        
        if (rs.next())
        {
            rs.updateTimestamp("orderDate", order.getOrderDate());
            rs.updateTimestamp("requiredDate", order.getRequiredDate());
            rs.updateTimestamp("shippedDate", order.getShippedDate());
            rs.updateString("customers_customerEmail", order.getCustomers_customerEmail());
            
            rs.updateRow();
        }

    }

     /**
     * Elimina un pedido en la base de datos basado en el objeto del argumento.
     * @param order Objeto de tipo order.
     */
    @Override
    public void delete(Order order) throws SQLException {
        Statement sentencia;
        
        sentencia = con.createStatement();
        String sqlStr = "DELETE FROM orders WHERE orderNumber = " + order.getOrderNumber();
        sentencia.executeUpdate(sqlStr);
    }

    /**
     * No Implementable.
     * @param order Objeto de tipo order
     * @return Objeto order de la base de datos.
     */
    @Override
    public Order get(Order order) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
