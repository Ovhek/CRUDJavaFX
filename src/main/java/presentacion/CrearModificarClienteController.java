/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joseb
 */
public class CrearModificarClienteController extends PresentationLayer implements Initializable {

    @FXML
    private Button btn_clienteAceptar;

    @FXML
    private Button btn_clienteCancelar;

    @FXML
    private TextField txtf_clienteEmail;

    @FXML
    private TextField txtf_clienteNac;

    @FXML
    private TextField txtf_clienteNombre;

    @FXML
    private TextField txtf_clienteSaldo;

    @FXML
    private TextField txtf_clienteTargeta;

    @FXML
    private TextField txtf_clienteTelf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void btnAceptarOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancerlarOnAction(ActionEvent event) {
        this.close();
    }

    @Override
    public void close() {
        Stage stage = (Stage) this.btn_clienteCancelar.getScene().getWindow();
        stage.close();

    }

}
