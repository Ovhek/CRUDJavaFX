/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import aplicacion.modelo.Customer;
import aplicacion.modelo.Order;
import aplicacion.modelo.OrderDetails;
import aplicacion.modelo.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *  DAO de OrderDetails
 */
public class OrderDetailsDAO extends DataLayer implements DAOInterface<OrderDetails>{

    public OrderDetailsDAO() throws SQLException {
    }

    /**
     * No implementable, es necesario el tener las primary keys para poder obtener los detalles de pedidos de forma lógica. Utilizar getAllByOrderProduct
     */
    @Override
    public List<OrderDetails> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

     /**
     * Obtiene los detalles del pedido pasado como parametro.
     * @param order Objeto de tipo order.
     * @return detalles del pedido.
     */
    public List<OrderDetails> getAllByOrderNumber(Order order) throws SQLException{
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        
        String consulta = "select * from orderdetails where orderNumber = "+order.getOrderNumber();
        ResultSet rs = this.getCon().createStatement().executeQuery(consulta);
        
        while(rs.next()){
            orderDetails.add(new OrderDetails(
                    rs.getInt("orderNumber"),
                    rs.getInt("productCode"),
                    rs.getInt("quantityOrdered"),
                    rs.getFloat("priceEach"),
                    rs.getInt("orderLineNumber")
            ));
        }
        return orderDetails;
    }
    
     /**
     * Guarda los detalles de pedido.
     * @param orderDetail Objeto de tipo orderDetail.
     */
    @Override
    public void save(OrderDetails orderDetail) throws SQLException {
        Statement sentencia;
        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        sentencia.executeQuery("SELECT * FROM orderdetails");
        ResultSet rs = sentencia.getResultSet();
        rs.last();
        int orderNumber = rs.getInt("orderLineNumber")+1;
        int orderLineNumber = rs.getInt("orderLineNumber")+1;
        rs.moveToInsertRow();
        rs.updateInt("orderNumber", orderNumber);
        rs.updateInt("productCode", orderDetail.getProductCode());
        rs.updateInt("quantitOrdered", orderDetail.getQuantityOrdered());
        rs.updateFloat("priceEach", orderDetail.getPriceEach());
        rs.updateInt("orderLineNumber",orderLineNumber);
        
        rs.insertRow();
    }

    /**
     * Actualiza el detalle del pedido 
     * @param orderDetail Objeto de tipo orderDetail.
     */
    @Override
    public void update(OrderDetails orderDetails) throws SQLException {
        Statement sentencia;
        
        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        //TODO: PRODUCT CODE
        sentencia.executeQuery("SELECT * FROM orderdetails where orderNumber = " + orderDetails.getOrderNumber() + " and productCode = " + orderDetails.getProductCode());
        ResultSet rs = sentencia.getResultSet();
        
        if (rs.next())
        {
            rs.updateInt("quantityOrdered", orderDetails.getQuantityOrdered());
            rs.updateFloat("priceEach", orderDetails.getPriceEach());
            
            rs.updateRow();
        }
    }

    @Override
    public void delete(OrderDetails orderDetails) throws SQLException {
        Statement sentencia;
        
        sentencia = con.createStatement();
        String sqlStr = "DELETE FROM orderDetails WHERE orderNumber = " + orderDetails.getOrderNumber() + " and productCode = " + orderDetails.getProductCode();
        sentencia.executeUpdate(sqlStr);
    }


    @Override
    public OrderDetails get(OrderDetails order) throws SQLException {
        OrderDetails orderDetails = null;
        String consulta = "select * from orderdetails where orderNumber = "+order.getOrderNumber() + " and productCode = "+order.getProductCode();
        ResultSet rs = this.getCon().createStatement().executeQuery(consulta);
        
        if(rs.next()){
            orderDetails = new OrderDetails(
                    rs.getInt("orderNumber"),
                    rs.getInt("productCode"),
                    rs.getInt("quantityOrdered"),
                    rs.getFloat("priceEach"),
                    rs.getInt("orderLineNumber")
            );
        }
        return orderDetails;
    }
}
