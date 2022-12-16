/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import Utils.LoadFXML;
import aplicacion.LogicLayerException;
import aplicacion.ProductsLogic;
import aplicacion.modelo.Product;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cole
 */
public class ProductosController extends PresentationLayer implements Initializable {

    
    // Creacion de observable list(Actuan a modo de arrayList pero en javaFx)
    // ArrayList elements para todo tipo de gestion con los parametros introducidos y listas
    // filtroListas servira para la barra de busqueda
    //Se asignan de clase playlist para trabajar con la clase y su atributos
    ObservableList<Product> elements = FXCollections.observableArrayList();
    
    @FXML
    private Button btnAñadir;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn columnCodigoPr, columnDescripcionPr, columnNombrePr, columnPrecioPr,columnStockPr;

    @FXML
    private TableView tblProductos;

    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       try {
            
           this.productsLogic = new ProductsLogic();
           List<Product> listProductos;
           
           listProductos = productsLogic.mostrarProductos();
           listProductos.forEach(x -> this.elements.add(x));
           
           //Establecemos un vinculo entre cada columna de la tabla y cada nombre de el getter de mi clase(Product)
           columnCodigoPr.setCellValueFactory(new PropertyValueFactory<>("ProductCode"));
           columnNombrePr.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
           columnDescripcionPr.setCellValueFactory(new PropertyValueFactory<>("ProductDescription"));
           columnStockPr.setCellValueFactory(new PropertyValueFactory<>("QuantityInStock"));
           columnPrecioPr.setCellValueFactory(new PropertyValueFactory<>("BuyPrice"));
           
           tblProductos.setItems(elements);
        } catch (LogicLayerException ex) {
            Logger.getLogger(ProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void onActionAdd(ActionEvent event) {
        
        //creamos un loader al que le pasaremos la ruta para ir a la pantalla que queremos 
        LoadFXML loader = new LoadFXML();
        loader.openNewWindow("/presentacion/productosCrearModificar.fxml");
    }
    
    @FXML
    void onActionModificar(ActionEvent event) {
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
