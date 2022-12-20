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
    
    //Constructor sin el code

    public Product(String productName, int quantityInStock, float buyPrice, String productDescription) {
        this.productName = productName;
        this.quantityInStock = quantityInStock;
        this.buyPrice = buyPrice;
        this.productDescription = productDescription;
    }
    

    //Creacion de los Getters y Setters
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
        Obtiene el nombre del producto.
        @return El nombre del producto.
    */
    public String getProductName() {
        return productName;
    }

    /**
        Establece el nombre del producto.
        @param productName El nombre del producto.
    */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    /**
        Obtiene la descripción del producto.
        @return La descripción del producto.
    */
    public String getProductDescription() {
        return productDescription;
    }

    /**
        Establece la descripción del producto.
        @param productDescription La descripción del producto.
    */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
        Obtiene la cantidad en stock.
        @return La cantidad en stock.
    */
    public int getQuantityInStock() {
        return quantityInStock;
    }

    /**
        Establece la cantidad en stock.
        @param quantityInStock La cantidad en stock.
    */
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    /**
        Obtiene el precio de compra.
        @return El precio de compra.
    */
    public float getBuyPrice() {
        return buyPrice;
    }

    /**
        Establece el precio de compra.
        @param buyPrice El precio de compra.
    */
    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }
        
}
