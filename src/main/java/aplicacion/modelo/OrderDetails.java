/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion.modelo;

/**
 *
 * @author Cole
 */
public class OrderDetails {
    private int orderNumber;
    private int productCode;
    private int quantityOrdered;
    private float priceEach;
    private int orderLineNumber;

    public OrderDetails(){
        
    }
    public OrderDetails(int orderNumber, int productCode, int quantityOrdered, float priceEach,int orderLineNumber) {
        this.orderNumber = orderNumber;
        this.productCode = productCode;
        this.quantityOrdered = quantityOrdered;
        this.priceEach = priceEach;
        this.orderLineNumber = orderLineNumber;
    }

    /**
        Obtiene el número de línea de orden.
        @return El número de línea de orden.
    */
    public int getOrderLineNumber() {
        return orderLineNumber;
    }

    /**
        Establece el número de línea de orden.
        @param orderLineNumber El número de línea de orden.
    */
    public void setOrderLineNumber(int orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    /**
        Obtiene el número de orden.
        @return El número de orden.
    */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
        Establece el número de orden.
        @param orderNumber El número de orden.
    */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
        Obtiene el código del producto.
        @return El código del producto.
    */
    public int getProductCode() {
        return productCode;
    }

    /**
        Establece el código del producto.
        @param productCode El código del producto.
    */
    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    /**
        Obtiene la cantidad pedida.
        @return La cantidad pedida.
    */
    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    /**
        Establece la cantidad pedida.
        @param quantityOrdered La cantidad ordenada.
    */
    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    /**
        Obtiene el precio individual.
        @return El precio individual.
    */
    public float getPriceEach() {
        return priceEach;
    }
    
    /**
        Establece el precio individual.
        @param priceEach El precio individual.
    */
    public void setPriceEach(float priceEach) {
        this.priceEach = priceEach;
    }

    
}
