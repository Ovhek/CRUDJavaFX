/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion.modelo;

import java.time.LocalDate;

/**
 *
 * Modelo de la base de datos. Customer.
 */
public class Customer {
    /**
     * Correo electronico del cliente (primary key)
     */
    private String customerEmail;
    /**
     * Id de la targeta de credito del cliente
     */
    private String idCard;
    /**
     * Nombre del cliente
     */
    private String customerName;
    /**
     * Numero de telefono del cliente
     */
    private String phone;
    /**
     * Saldo del cliente
     */
    private double creditLimit;
    /**
     * Fecha de nacimiento del cliente
     */
    private LocalDate birthDate;

    /**
     *Constructor de Costumer
     */
    public Customer() {
    }

    /**
     *
     * Constructor de Customer que recibe todos los parametros
     * 
     * @param customerEmail Correo electronico del cliente (primary key)
     * @param idCard Id de la targeta de credito del cliente
     * @param customerName Nombre del cliente
     * @param phone Numero de telefono del cliente
     * @param creditLimit Saldo del cliente
     * @param birthDate Fecha de nacimiento
     */
    public Customer(String customerEmail, String idCard, String customerName, String phone, double creditLimit, LocalDate birthDate) {
        this.customerEmail = customerEmail;
        this.idCard = idCard;
        this.customerName = customerName;
        this.phone = phone;
        this.creditLimit = creditLimit;
        this.birthDate = birthDate;
    }

    /**
     *Devuelve el correo electronico del cliente
     * @return el correo electronico del cliente
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     *Devuelve la id de la targeta de credito del cliente
     * @return la id de la targeta del cliente
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     *Devuelve el nombre del cliente
     * @return el nombre del cliente
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *Devuelve el numero de telefono 
     * @return numero de telefono
     */
    public String getPhone() {
        return phone;
    }

    /**
     *Devuelve el saldo del cliente
     * @return el saldo del cliente
     */
    public double getCreditLimit() {
        return creditLimit;
    }

    /**
     *Devuelve la fecha de nacimiento del cliente
     * @return la fecha de nacimiento del cliente
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     *Asigna un correo electronico al cliente
     * @param customerEmail
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     *Asgina una targeta de credito al cliente
     * @param idCard
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     *Asigna un nombre al cliente
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    /**
     *Asgina un numero de telefono al cliente
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *Asigna un saldo al cliente
     * @param creditLimit
     */
    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     *Asigna una fecha de nacimiento al cliente
     * @param birthDate
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     *Devuelve los atributos del cliente
     * @return
     */
    @Override
    public String toString() {
        return "Customer{" + "customerEmail=" + customerEmail + ", idCard=" + idCard + ", customerName=" + customerName + ", phone=" + phone + ", creditLimit=" + creditLimit + ", birthDate=" + birthDate + '}';
    }
    
    
}
