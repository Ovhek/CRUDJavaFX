/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import datos.AppConfigDAO;
import datos.CustomersDAO;
import datos.OrderDetailsDAO;
import datos.OrdersDAO;
import datos.ProductsDAO;
import java.sql.SQLException;

/**
 *
 * @author Cole
 */
public abstract class LogicLayer {
        
    private AppConfigDAO appConfigDAO;
    private CustomersDAO customersDAO;
    private OrderDetailsDAO orderDetailsDAO;
    private OrdersDAO ordersDAO;
    private ProductsDAO productsDAO;

    /***
     * Connecta con la capa de datos y abre la conexión
     * 
     * @throws aplicacion.LogicLayerException
     */
    public LogicLayer() throws LogicLayerException { 
        try {
            // inicialitzem capa dades
            this.appConfigDAO = new AppConfigDAO();
            this.customersDAO = new CustomersDAO();
            this.orderDetailsDAO = new OrderDetailsDAO();
            this.ordersDAO = new OrdersDAO();
            this.productsDAO = new ProductsDAO();
        } catch (SQLException ex) {
            
            throw new LogicLayerException("Error inicialitzant capa de dades: " + formatSQLException(ex));
        }
    }
    
    public String formatSQLException(SQLException ex)
    {
        String ret = "";
        
        ret += "SQLException : " + ex.getMessage() + System.lineSeparator();
        ret += "SQLState     : " + ex.getSQLState()+ System.lineSeparator();
        ret += "VendorCode   : " + ex.getErrorCode() + System.lineSeparator();
        ret += "LocalMessage : " + ex.getLocalizedMessage() + System.lineSeparator();
        
        return ret;
    }

    public AppConfigDAO getAppConfigDAO() {
        return appConfigDAO;
    }

    public CustomersDAO getCustomersDAO() {
        return customersDAO;
    }

    public OrderDetailsDAO getOrderDetailsDAO() {
        return orderDetailsDAO;
    }

    public OrdersDAO getOrdersDAO() {
        return ordersDAO;
    }

    public ProductsDAO getProductsDAO() {
        return productsDAO;
    }
    
    /***
     * Permite a las clases reimplementar este metodo de cierre. 
     * 
     */
    public abstract void close() throws LogicLayerException;    
}
