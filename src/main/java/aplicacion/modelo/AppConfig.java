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
    float defaultCreditLimit;
    int defaultQuantityInStock;
    int defaultQuantityOrdered;
    int defaultProductBanefit;
    int minShippingHours;
    int minCustomerAge;
    int maxLinesPerOrder;
    float maxOrderAmount;

    public AppConfig() {
    }

    public float getDefaultCreditLimit() {
        return defaultCreditLimit;
    }

    public void setDefaultCreditLimit(float defaultCreditLimit) {
        this.defaultCreditLimit = defaultCreditLimit;
    }

    public int getDefaultQuantityInStock() {
        return defaultQuantityInStock;
    }

    public void setDefaultQuantityInStock(int defaultQuantityInStock) {
        this.defaultQuantityInStock = defaultQuantityInStock;
    }

    public int getDefaultQuantityOrdered() {
        return defaultQuantityOrdered;
    }

    public void setDefaultQuantityOrdered(int defaultQuantityOrdered) {
        this.defaultQuantityOrdered = defaultQuantityOrdered;
    }

    public int getDefaultProductBanefit() {
        return defaultProductBanefit;
    }

    public void setDefaultProductBanefit(int defaultProductBanefit) {
        this.defaultProductBanefit = defaultProductBanefit;
    }

    public int getMinShippingHours() {
        return minShippingHours;
    }

    public void setMinShippingHours(int minShippingHours) {
        this.minShippingHours = minShippingHours;
    }

    public int getMinCustomerAge() {
        return minCustomerAge;
    }

    public void setMinCustomerAge(int minCustomerAge) {
        this.minCustomerAge = minCustomerAge;
    }

    public int getMaxLinesPerOrder() {
        return maxLinesPerOrder;
    }

    public void setMaxLinesPerOrder(int maxLinesPerOrder) {
        this.maxLinesPerOrder = maxLinesPerOrder;
    }

    public float getMaxOrderAmount() {
        return maxOrderAmount;
    }

    public void setMaxOrderAmount(float maxOrderAmount) {
        this.maxOrderAmount = maxOrderAmount;
    }
    
    
}
