/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion.modelo;

import java.time.LocalDate;

/**
 *
 * @author Cole
 */
public class Customer {
    
    private String customerEmail;
    private String idCard;
    private String customerName;
    private String phone;
    private double creditLimit;
    private LocalDate birthDate;

    public Customer() {
    }

    public Customer(String customerEmail, String idCard, String customerName, String phone, double creditLimit, LocalDate birthDate) {
        this.customerEmail = customerEmail;
        this.idCard = idCard;
        this.customerName = customerName;
        this.phone = phone;
        this.creditLimit = creditLimit;
        this.birthDate = birthDate;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhone() {
        return phone;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerEmail=" + customerEmail + ", idCard=" + idCard + ", customerName=" + customerName + ", phone=" + phone + ", creditLimit=" + creditLimit + ", birthDate=" + birthDate + '}';
    }
    
    
}
