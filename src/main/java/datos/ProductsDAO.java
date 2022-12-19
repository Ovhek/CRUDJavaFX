/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import aplicacion.modelo.Customer;
import aplicacion.modelo.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Cole
 */
public class ProductsDAO extends DataLayer implements DAOInterface<Product> {

    public ProductsDAO() throws SQLException {
    }


    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> ret = new ArrayList<>();
        
        Statement sentencia;
        
        sentencia = this.getCon().createStatement();
        sentencia.executeQuery("SELECT * FROM products");
        ResultSet rs = sentencia.getResultSet();
        while (rs.next()){
            ret.add(new Product(rs.getInt("productCode"), rs.getString("productName"), rs.getString("productDescription"), rs.getInt("quantityInStock"), rs.getFloat("buyPrice")));
        }
        
        return ret;
    }

    @Override
    public void save(Product p) throws SQLException {
        Statement sentencia;
        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        sentencia.executeQuery("SELECT * FROM products");
        ResultSet rs = sentencia.getResultSet();
        int id = 0;
        if(rs.next()){
            rs.last();
            id = rs.getInt("productCode")+1;
        }

        rs.moveToInsertRow();
        rs.updateInt("ProductCode", id);
        rs.updateString("ProductName", p.getProductName());
        rs.updateString("ProductDescription", p.getProductDescription());
        rs.updateInt("QuantityInStock", p.getQuantityInStock());
        rs.updateFloat("BuyPrice", p.getBuyPrice());
        rs.insertRow();
        
    }
    
    @Override
    public void update(Product p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Product p) throws SQLException {
        Statement sentencia;
        sentencia = con.createStatement();
        String sqlStr = "DELETE FROM products WHERE productCode = " + p.getProductCode();
        sentencia.executeUpdate(sqlStr);
        
    }

    @Override
    public Product get(Product p) throws SQLException {
        Product ret = new Product();
        
        Statement sentencia;
        
        sentencia = this.getCon().createStatement();
        sentencia.execute("SELECT * FROM products WHERE productName = '" + p.getProductName()+"'");
        ResultSet rs = sentencia.getResultSet();
        if(rs.next()){
            
            ret = new Product(rs.getInt("productCode"), rs.getString("productName"), rs.getString("productDescription"), rs.getInt("quantityInStock"), rs.getFloat("buyPrice"));
        }
        
        return ret;
    }
    
    public Product getByProductCode(String productCode) throws SQLException {
        Product ret = new Product();
        
        Statement sentencia;
        
        sentencia = this.getCon().createStatement();
        sentencia.execute("SELECT * FROM products WHERE productCode = '" + productCode+"'");
        ResultSet rs = sentencia.getResultSet();
        if(rs.next()){
            ret = new Product(rs.getInt("productCode"), rs.getString("productName"), rs.getString("productDescription"), rs.getInt("quantityInStock"), rs.getFloat("buyPrice"));
        }
        
        return ret;
    }

}
