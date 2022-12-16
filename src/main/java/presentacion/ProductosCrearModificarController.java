/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author bhugo
 */
public class ProductosCrearModificarController extends PresentationLayer implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextArea editDescripcion;

    @FXML
    private TextField editNombre;

    @FXML
    private TextField editPrecio;

    @FXML
    private TextField editStock;

    @FXML
    private Label txtDescripcion;

    @FXML
    private Label txtNombre;

    @FXML
    private Label txtPrecio;

    @FXML
    private Label txtStock;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /*@FXML
    void onActionAceptar(ActionEvent event) {

    }

    @FXML
    void onActionCancelar(ActionEvent event) {

    }*/    

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
