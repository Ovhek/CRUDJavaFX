/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion.modelo;

/**
 *
 * @author Cole
 */
public class Product {
    //Creacion de variables
    private int productCode;
    private String productName;
    private String productDescription;
    public int quantityInStock;
    public float buyPrice;

    //Creacion de los constructores
    public Product(int productCode, String productName, String productDescription, int quantityInStock, float buyPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.productDescription = productDescription;
        this.quantityInStock = quantityInStock;
        this.buyPrice = buyPrice;
    }

    public Product() {
    }

    //Creacion de los Getters y Setters
    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Override
    public String toString() {
        return "Product{" + "productCode=" + productCode + ", productName=" + productName + ", productDescription=" + productDescription + ", quantityInStock=" + quantityInStock + ", buyPrice=" + buyPrice + '}';
    }
    
    
}
