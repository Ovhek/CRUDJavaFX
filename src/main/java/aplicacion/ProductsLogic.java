/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import aplicacion.modelo.AppConfig;
import aplicacion.modelo.Product;
import datos.ProductsDAO;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;
import presentacion.AppConfigController;

/**
 *
 * @author Cole
 */
public class ProductsLogic extends LogicLayer {

    /**
     * Lista observable de productos.
     */
    ObservableList<Product> listaProductos;

    /**
     * Constructor de la clase.
     *
     * @throws LogicLayerException si se produce un error en la capa lógica.
     */
    public ProductsLogic() throws LogicLayerException {
        super();
    }

    public int verStockDefault(){
        return ((AppConfigController)Manager.getInstance().getController(AppConfigController.class)).getDefaultQuantityInStock();
    }
    /**
     * Método que muestra todos los productos.
     *
     * @return lista de productos
     * @throws LogicLayerException si se produce un error en la capa lógica al
     * recuperar la lista de productos.
     */
    public List<Product> mostrarProductos() throws LogicLayerException {
        try {
            List<Product> ret = null;

            ret = this.getProductsDAO().getAll();

            return ret;
        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades recuperant llista d'oficines : " + ex.toString());
        }
    }

    /**
     * Método que añade un producto a la base de datos.
     *
     * @param p producto a añadir.
     * @throws SQLException si se produce un error al añadir el producto.
     */
    public void addProducto(Product p) throws SQLException {
        this.getProductsDAO().save(p);
    }

    /**
     * Método que actualiza un producto de la base de datos.
     *
     * @param p producto a actualizar.
     * @throws LogicLayerException si se produce un error en la capa lógica al
     * actualizar el producto.
     */
    public void updateProduto(Product p) throws LogicLayerException {
        try {
            this.getProductsDAO().update(p);
        } catch (SQLException e) {
            throw new LogicLayerException(e.getMessage());
        }
    }

    /**
     * Método que obtiene un producto de la base de datos.
     *
     * @param p producto a obtener.
     * @return producto obtenido.
     * @throws SQLException si se produce un error al obtener el producto.
     */
    public Product getProducto(Product p) throws SQLException {
        return this.getProductsDAO().get(p);
    }

    /**
     * Método que elimina un producto de la base de datos.
     *
     * @param p producto a obtener.
     * @return producto obtenido.
     * @throws SQLException si se produce un error al obtener el producto.
     */
    public void eliminaProducto(Product p) throws SQLException {
        this.getProductsDAO().delete(p);
    }

    @Override
    public void close() throws LogicLayerException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
