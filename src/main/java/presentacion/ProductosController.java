/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import Utils.LoadFXML;
import aplicacion.Manager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Cole
 */
public class ProductosController extends PresentationLayer implements Initializable {

    @FXML
    private VBox vBoxProductos;
    @FXML
    private TableView<?> tblProductos;
    @FXML
    private TableColumn<?, ?> columnCodigoPr;
    @FXML
    private TableColumn<?, ?> columnNombrePr;
    @FXML
    private TableColumn<?, ?> columnDescripcionPr;
    @FXML
    private TableColumn<?, ?> columnStockPr;
    @FXML
    private TableColumn<?, ?> columnPrecioPr;
    @FXML
    private HBox hBoxProductos;
    @FXML
    private Button btnAñadir;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Manager.getInstance().addController(this);
    } 

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
