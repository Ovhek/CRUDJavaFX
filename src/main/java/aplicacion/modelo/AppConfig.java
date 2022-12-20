/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion.modelo;

/**
 *
 * Modelo de la base de datos. AppConfig.
 */
public class AppConfig {
    /**
     * Límite de crédito por defecto para los clientes.
     */
    float defaultCreditLimit;
    /**
     * Cantidad en stock por defecto para los productos.
     */
    int defaultQuantityInStock;
    /**
     * Cantidad pedida por defecto para los productos.
     */
    int defaultQuantityOrdered;
    /**
     * Beneficio por defecto para los productos.
     */
    int defaultProductBanefit;
    /**
     * Número mínimo de horas de envío.
     */
    int minShippingHours;
    /**
     * Edad mínima para ser cliente.
     */
    int minCustomerAge;
    /**
     * Número máximo de líneas por pedido.
     */
    int maxLinesPerOrder;
    /**
     * Importe máximo por pedido.
     */
    float maxOrderAmount;

    /**
     * Constructor que recibe todos los parámetros de configuración.
     *
     * @param defaultCreditLimit Límite de crédito por defecto para los clientes.
     * @param defaultQuantityInStock Cantidad en stock por defecto para los productos.
     * @param defaultQuantityOrdered Cantidad pedida por defecto para los productos.
     * @param defaultProductBanefit Beneficio por defecto para los productos.
     * @param minShippingHours Número mínimo de horas de envío.
     * @param minCustomerAge Edad mínima para ser cliente.
     * @param maxLinesPerOrder Número máximo de líneas por pedido.
     * @param maxOrderAmount Importe máximo por pedido.
     */
    public AppConfig(float defaultCreditLimit, int defaultQuantityInStock, int defaultQuantityOrdered, int defaultProductBanefit, int minShippingHours, int minCustomerAge, int maxLinesPerOrder, float maxOrderAmount) {
        this.defaultCreditLimit = defaultCreditLimit;
        this.defaultQuantityInStock = defaultQuantityInStock;
        this.defaultQuantityOrdered = defaultQuantityOrdered;
        this.defaultProductBanefit = defaultProductBanefit;
        this.minShippingHours = minShippingHours;
        this.minCustomerAge = minCustomerAge;
        this.maxLinesPerOrder = maxLinesPerOrder;
        this.maxOrderAmount = maxOrderAmount;
    }

    /**
     * Constructor vacío.
    */
    public AppConfig() {
    }

    /**
     * Devuelve el Límite de crédito por defecto para los clientes.
     * @return Límite de crédito por defecto para los clientes.
     */
    public float getDefaultCreditLimit() {
        return defaultCreditLimit;
    }

     /**
     * Establece el Límite de crédito por defecto para los clientes.
     * @param defaultCreditLimit Límite de crédito por defecto para los clientes.
     */
    public void setDefaultCreditLimit(float defaultCreditLimit) {
        this.defaultCreditLimit = defaultCreditLimit;
    }

    /**
     * Devuelve la Cantidad en stock por defecto para los productos.
     * @return Cantidad en stock por defecto para los productos.
     */
    public int getDefaultQuantityInStock() {
        return defaultQuantityInStock;
    }

     /**
     * Establece la Cantidad en stock por defecto para los productos.
     * @param defaultQuantityInStock  Cantidad en stock por defecto para los productos.
     */
    public void setDefaultQuantityInStock(int defaultQuantityInStock) {
        this.defaultQuantityInStock = defaultQuantityInStock;
    }

     /**
     * Devuelve la Cantidad por defecto para los productos.
     * @return Cantidad pedida por defecto para los productos.
     */
    public int getDefaultQuantityOrdered() {
        return defaultQuantityOrdered;
    }

     /**
     * Establece la Cantidad por defecto para los productos.
     * @param defaultQuantityOrdered  Cantidad por defecto para los productos.
     */
    public void setDefaultQuantityOrdered(int defaultQuantityOrdered) {
        this.defaultQuantityOrdered = defaultQuantityOrdered;
    }

     /**
     * Devuelve el Beneficio por defecto para los productos.
     * @return Beneficio por defecto para los productos.
     */
    public int getDefaultProductBanefit() {
        return defaultProductBanefit;
    }

    /**
     * Establece el Beneficio por defecto para los productos.
     * @param defaultProductBanefit Beneficio por defecto para los productos.
     */
    public void setDefaultProductBanefit(int defaultProductBanefit) {
        this.defaultProductBanefit = defaultProductBanefit;
    }

     /**
     * Devuelve el Número mínimo de horas de envío.
     * @return Número mínimo de horas de envío.
     */
    public int getMinShippingHours() {
        return minShippingHours;
    }

    /**
     * Establece el Número mínimo de horas de envío.
     * @param minShippingHours  Número mínimo de horas de envío.
     */
    public void setMinShippingHours(int minShippingHours) {
        this.minShippingHours = minShippingHours;
    }

    /**
     * Devuelve la Edad mínima para ser cliente.
     * @return  Edad mínima para ser cliente.
     */
    public int getMinCustomerAge() {
        return minCustomerAge;
    }
    
    /**
     * Establece la Edad mínima para ser cliente.
     * @param minCustomerAge  Edad mínima para ser cliente.
     */
    public void setMinCustomerAge(int minCustomerAge) {
        this.minCustomerAge = minCustomerAge;
    }

    /**
     * Devuelve el Número máximo de líneas por pedido.
     * @return  Número máximo de líneas por pedido.
     */
    public int getMaxLinesPerOrder() {
        return maxLinesPerOrder;
    }
    
    /**
     * Establece el Número máximo de líneas por pedido.
     * @param maxLinesPerOrder Número máximo de líneas por pedido.
     */
    public void setMaxLinesPerOrder(int maxLinesPerOrder) {
        this.maxLinesPerOrder = maxLinesPerOrder;
    }

     /**
     * Devuelve el Importe máximo por pedido.
     * @return Importe máximo por pedido.
     */
    public float getMaxOrderAmount() {
        return maxOrderAmount;
    }

     /**
     * Establece el Importe máximo por pedido.
     * @param maxOrderAmount  Importe máximo por pedido.
     */
    public void setMaxOrderAmount(float maxOrderAmount) {
        this.maxOrderAmount = maxOrderAmount;
    }
}
