/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion.modelo;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *  Modelo de las ordenes.
 * @author Cole
 */
public class Order {
    private int orderNumber;
    private Timestamp orderDate;
    private Timestamp requiredDate;
    private Timestamp shippedDate;
    private String customers_customerEmail;
    private ArrayList<OrderDetails> orderDetails;

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }
    
    public void addOrderDetailsToOrder(OrderDetails orderDetail) {
        orderDetails.add(orderDetail);
    }


    public Order() {
        orderDetails = new ArrayList<OrderDetails>();
    }

    public Order(int orderNumber, Timestamp orderDate, Timestamp requiredDate, Timestamp shippedDate, String customers_customerEmail) {
        orderDetails = new ArrayList<OrderDetails>();
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.customers_customerEmail = customers_customerEmail;
    }
    
    /**
        Obtiene el número de orden.
        @return El número de orden.
    */
    public int getOrderNumber() {
        return orderNumber;
    }
    /**
        Establece los detalles de la orden.
        @param orderDetails Los detalles de la orden.
    */
    public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    /**
        Establece el número de orden.
        @param orderNumber El número de orden.
    */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
        Obtiene la fecha de la orden.
        @return La fecha de la orden.
    */
    public Timestamp getOrderDate() {
        return orderDate;
    }

    /**
        Establece la fecha de la orden.
        @param orderDate La fecha de la orden.
    */
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    /**
        Obtiene la fecha de recibir.
        @return La fecha de recibir.
    */
    public Timestamp getRequiredDate() {
        return requiredDate;
    }

    /**
        Establece la fecha requerida.
        @param requiredDate La fecha requerida.
    */
    public void setRequiredDate(Timestamp requiredDate) {
        this.requiredDate = requiredDate;
    }

    /**
        Obtiene la fecha de envío.
        @return La fecha de envío.
    */
    public Timestamp getShippedDate() {
        return shippedDate;
    }

    /**
        Establece la fecha de envío.
        @param shippedDate La fecha de envío.
    */
    public void setShippedDate(Timestamp shippedDate) {
        this.shippedDate = shippedDate;
    }
    
    /**
        Obtiene el correo electrónico del cliente.
        @return El correo electrónico del cliente.
    */
    public String getCustomers_customerEmail() {
        return customers_customerEmail;
    }

    /**
        Establece el correo electrónico del cliente.
        @param customers_customerEmail El correo electrónico del cliente.
    */
    public void setCustomers_customerEmail(String customers_customerEmail) {
        this.customers_customerEmail = customers_customerEmail;
    }
}
