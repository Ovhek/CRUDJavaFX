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

    public int getOrderNumber() {
        return orderNumber;
    }
    public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Timestamp requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Timestamp getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Timestamp shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getCustomers_customerEmail() {
        return customers_customerEmail;
    }

    public void setCustomers_customerEmail(String customers_customerEmail) {
        this.customers_customerEmail = customers_customerEmail;
    }
}
