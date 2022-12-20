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
 * Clase abstracta que representa la capa de lógica del sistema.
 */
public abstract class LogicLayer {
        
    private AppConfigDAO appConfigDAO;
    private CustomersDAO customersDAO;
    private OrderDetailsDAO orderDetailsDAO;
    private OrdersDAO ordersDAO;
    private ProductsDAO productsDAO;

    /***
     * Connecta con la capa de datos y abre la conexión
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
    
     /**
     * Método que formatea una excepción SQL para facilitar su lectura.
     *
     * @param ex Excepción SQL a formatear.
     * @return Cadena de texto con la excepción formateada.
     */
    public String formatSQLException(SQLException ex)
    {
        String ret = "";
        
        ret += "SQLException : " + ex.getMessage() + System.lineSeparator();
        ret += "SQLState     : " + ex.getSQLState()+ System.lineSeparator();
        ret += "VendorCode   : " + ex.getErrorCode() + System.lineSeparator();
        ret += "LocalMessage : " + ex.getLocalizedMessage() + System.lineSeparator();
        
        return ret;
    }

    /**
     * Método que devuelve el objeto que representa el acceso a la configuración de la aplicación en la base de datos.
     *
     * @return Objeto que representa la configuración de la aplicación en la base de datos.
     */
    public AppConfigDAO getAppConfigDAO() {
        return appConfigDAO;
    }
    
    /**
     * Método que devuelve el objeto que representa el acceso a los clientes en la base de datos.
     *
     * @return Objeto que representa el acceso a los clientes en la base de datos.
     */
    public CustomersDAO getCustomersDAO() {
        return customersDAO;
    }
    
    /**
     * Método que obtiene el objeto que representa el acceso a los detalles de pedidos en la base de datos.
     *
     * @return Objeto que representa el acceso a los detalles de pedidos en la base de datos.
     */
    public OrderDetailsDAO getOrderDetailsDAO() {
        return orderDetailsDAO;
    }

     /**
     * Método que obtiene el objeto que representa el acceso a los pedidos en la base de datos.
     *
     * @return Objeto que representa el acceso a los pedidos en la base de datos.
     */
    public OrdersDAO getOrdersDAO() {
        return ordersDAO;
    }

     /**
     * Método que obtiene el objeto que representa el acceso a los productos en la base de datos.
     *
     * @return Objeto que representa el acceso a los productos en la base de datos.
     */
    public ProductsDAO getProductsDAO() {
        return productsDAO;
    }
    
    /***
     * Permite a las clases reimplementar este metodo de cierre. 
     */
    public abstract void close() throws LogicLayerException;    
}
