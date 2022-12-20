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
        while (rs.next()) {
            ret.add(new Product(rs.getInt("productCode"), rs.getString("productName"), rs.getString("productDescription"), rs.getInt("quantityInStock"), rs.getFloat("buyPrice")));
        }

        return ret;
    }

    @Override
    public void save(Product p) throws SQLException {
        //abrir conexion
        this.createConection();
        Statement sentencia;
        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        sentencia.executeQuery("SELECT * FROM products");
        ResultSet rs = sentencia.getResultSet();
        rs.last();
        int id = rs.getInt("productCode") + 1;
        rs.moveToInsertRow();
        rs.updateInt("productCode", id);
        rs.updateString("productName", p.getProductName());
        rs.updateString("productDescription", p.getProductDescription());
        rs.updateInt("quantityInStock", p.getQuantityInStock());
        rs.updateFloat("buyPrice", p.getBuyPrice());
        rs.insertRow();

        con.close();
    }

    @Override
    public void update(Product p) throws SQLException {
        //abrir conexion
        this.createConection();
        //Creamos sentencia
        Statement sentencia;
        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        sentencia.executeQuery("SELECT * FROM products WHERE productCode = " + p.getProductCode());
        ResultSet rs = sentencia.getResultSet();

        //Cogemos el producto y le actualizamos cada uno de sus parametros
        if (rs.next()) {
            rs.updateString("productName", p.getProductName());
            rs.updateString("productDescription", p.getProductDescription());
            rs.updateInt("quantityInStock", p.getQuantityInStock());
            rs.updateDouble("buyPrice", p.getBuyPrice());
            //Actualizamos el producto
            rs.updateRow();
        }
        con.close();
    }

    @Override
    public void delete(Product p) throws SQLException {
        //abrir conexion
        this.createConection();
        Statement sentencia;
        sentencia = con.createStatement();
        String sqlStr = "DELETE FROM products WHERE productCode = " + p.getProductCode();
        sentencia.executeUpdate(sqlStr);
        con.close();
    }
    

    @Override
    public Product get(Product p) throws SQLException {
        //abrir conexion
        this.createConection();
        Product ret = new Product();

        Statement sentencia;

        sentencia = this.getCon().createStatement();
        sentencia.execute("SELECT * FROM products WHERE productName = '" + p.getProductName() + "'");
        ResultSet rs = sentencia.getResultSet();
        if (rs.next()) {

            ret = new Product(rs.getInt("productCode"), rs.getString("productName"), rs.getString("productDescription"), rs.getInt("quantityInStock"), rs.getFloat("buyPrice"));
        }

        con.close();
        return ret;
        
    }

}
