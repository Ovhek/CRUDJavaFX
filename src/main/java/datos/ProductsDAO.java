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

    /**
     * Método que obtiene todos los productos de la base de datos para
     * mostrarlos en el tableView
     *
     * @return lista de productos.
     * @throws SQLException si se produce un error al obtener los productos.
     */
    @Override
    public List<Product> getAll() throws SQLException {
        //abrir conexion
        this.createConection();
        List<Product> ret = new ArrayList<>();

        Statement sentencia;

        sentencia = this.getCon().createStatement();
        sentencia.executeQuery("SELECT * FROM products");
        ResultSet rs = sentencia.getResultSet();
        while (rs.next()) {
            ret.add(new Product(rs.getInt("productCode"), rs.getString("productName"), rs.getString("productDescription"), rs.getInt("quantityInStock"), rs.getFloat("buyPrice")));
        }

        con.close();
        return ret;
    }

    /**
     * Método que crea un producto en la base de datos.
     *
     * @param p producto a crear.
     * @throws SQLException si se produce un error al guardar el producto.
     */
    @Override
    public void save(Product p) throws SQLException {
        //abrir conexion
        this.createConection();
        //Creamos sentencia
        Statement sentencia;
        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        sentencia.executeQuery("SELECT * FROM products");
        ResultSet rs = sentencia.getResultSet();
        //Encontramos el ultimo producto

        //Sacamos el id del ultimo en una variable y le sumamos 1
        //Asi ese ya se lo podremos asignar a el nuevo producto
        int productCode = 0;
        if (rs.next()) {
            rs.last();
            productCode = rs.getInt("productCode") + 1;
        }

        //como hemos encontrado el ultimo producto ahora pasamos a la siguiente 
        //casilla en blanco y ahi sera donde insertaremos productos
        rs.moveToInsertRow();
        //Escribimos todos los parametros para añadir el producto
        rs.updateInt("productCode", productCode);
        rs.updateString("productName", p.getProductName());
        rs.updateString("productDescription", p.getProductDescription());
        rs.updateInt("quantityInStock", p.getQuantityInStock());
        rs.updateFloat("buyPrice", p.getBuyPrice());
        rs.insertRow();

        //Cerrar conexion
        con.close();
    }

    /**
     * Método que actualiza un producto en la base de datos.
     *
     * @param p producto a actualizar.
     * @throws SQLException si se produce un error al actualizar el producto.
     */
    @Override
    public void update(Product p) throws SQLException {
        //abrir conexion
        this.createConection();
        //Creamos sentencia
        Statement sentencia;
        // Ejecutar consulta para actualizar el producto deseado por ID
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

        //Cerrar conexion
        con.close();
    }

    public Product getByProductCode(String productCode) throws SQLException {
        Product ret = new Product();

        Statement sentencia;

        sentencia = this.getCon().createStatement();
        sentencia.execute("SELECT * FROM products WHERE productCode = '" + productCode + "'");
        ResultSet rs = sentencia.getResultSet();
        if (rs.next()) {
            ret = new Product(rs.getInt("productCode"), rs.getString("productName"), rs.getString("productDescription"), rs.getInt("quantityInStock"), rs.getFloat("buyPrice"));
        }

        return ret;
    }

    /**
     * Método que elimina un producto de la base de datos.
     *
     * @param p producto a eliminar.
     * @throws SQLException si se produce un error al eliminar el producto.
     */
    @Override
    public void delete(Product p) throws SQLException {
        //abrir conexion
        this.createConection();
        //creamos sentencia
        Statement sentencia;
        // Ejecutar consulta para eliminar el producto deseado por ID
        sentencia = con.createStatement();
        String sqlStr = "DELETE FROM products WHERE productCode = " + p.getProductCode();
        //Actualizamos
        sentencia.executeUpdate(sqlStr);
        //Cerrar conexion
        con.close();
    }

    /**
     * Método que obtiene un producto específicado por ID en la base de datos.
     *
     * @param p producto a obtener.
     * @return producto obtenido.
     * @throws SQLException si se produce un error al obtener el producto.
     */
    @Override
    public Product get(Product p) throws SQLException {
        //abrir conexion
        this.createConection();
        //Creamos un producto
        Product ret = new Product();

        //creamos sentencia
        Statement sentencia;

        // Ejecutar consulta para obtener producto con el nombre especificado
        sentencia = this.getCon().createStatement();
        sentencia.execute("SELECT * FROM products WHERE productName = '" + p.getProductName() + "'");
        ResultSet rs = sentencia.getResultSet();

        // Si se encontró un producto, asignar sus valores al producto creado
        if (rs.next()) {

            ret = new Product(rs.getInt("productCode"), rs.getString("productName"), rs.getString("productDescription"), rs.getInt("quantityInStock"), rs.getFloat("buyPrice"));
        }

        //cerrar conexion
        con.close();
        return ret;

    }

}
