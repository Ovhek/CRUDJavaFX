/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import Utils.LoadFXML;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cole
 */
public class ProductosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnAñadir;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<?, ?> columnCodigoPr;

    @FXML
    private TableColumn<?, ?> columnDescripcionPr;

    @FXML
    private TableColumn<?, ?> columnNombrePr;

    @FXML
    private TableColumn<?, ?> columnPrecioPr;

    @FXML
    private TableColumn<?, ?> columnStockPr;

    @FXML
    private TableView<?> tblProductos;
    @FXML
    private VBox vBoxProductos;
    @FXML
    private HBox hBoxProductos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
}
