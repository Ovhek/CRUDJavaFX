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
    public void save(Product t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Product t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Product t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Product get(Product t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
