/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import aplicacion.modelo.Product;
import datos.ProductsDAO;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Cole
 */
public class ProductsLogic extends LogicLayer{
    
    ObservableList<Product> listaProductos;

    public ProductsLogic() throws LogicLayerException {
    }

    public List<Product> mostrarProductos() throws LogicLayerException{
        try{
            List<Product> ret = null;
            
            ret = this.getProductsDAO().getAll();
            
            return ret;
        } catch (SQLException ex){
            throw new LogicLayerException("Error de capa de dades recuperant llista d'oficines : " + ex.toString());
        }
    }
    
    public void addProducto(Product p) throws SQLException{
        this.getProductsDAO().save(p);
    }
    
    public Product getProducto(Product p) throws SQLException{
        return this.getProductsDAO().get(p);
    }
    
    public void eliminaProducto(Product p) throws SQLException{
        this.getProductsDAO().delete(p);
    }

    @Override
    public void close() throws LogicLayerException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
