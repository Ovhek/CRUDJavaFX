/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import aplicacion.CustomerAgeException;
import aplicacion.CustomersLogic;
import aplicacion.LogicLayerException;
import aplicacion.Manager;
import aplicacion.modelo.Customer;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    private ArrayList<TextField> text_fields;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager.getInstance().addController(this);
        text_fields = new ArrayList<>(
                Arrays.asList(
                        txtf_clienteEmail,
                        txtf_clienteNac,
                        txtf_clienteNombre,
                        txtf_clienteSaldo,
                        txtf_clienteTargeta,
                        txtf_clienteTelf));
    }

    @FXML
    void btnAceptarOnAction(ActionEvent event) {
        if (checkTextFields()) {
            try {
                this.customersLogic = new CustomersLogic();
                String customerEmail = txtf_clienteEmail.getText();
                String idCard = txtf_clienteTargeta.getText();
                String customerName = txtf_clienteNombre.getText();
                String phone = txtf_clienteTelf.getText();
                double credit = Double.parseDouble(txtf_clienteSaldo.getText());
                LocalDate birthDate = Utils.Utils.stringToDate(txtf_clienteNac.getText());
                customersLogic.introducirCliente(customerEmail, idCard, customerName, phone, credit, birthDate);
                Customer cliente = new Customer(customerEmail, idCard, customerName, phone, credit, birthDate);
                ((ClientesController) Manager.getInstance().getController(ClientesController.class)).insertItem(cliente);
                this.close();
            } catch (LogicLayerException e) {
                Utils.Utils.showErrorAlert(e.getMessage());
       
            } catch (CustomerAgeException e) {
                Utils.Utils.showInfoAlert(e.getMessage());
            }
        } else {
            Utils.Utils.showInfoAlert("Hay campos vacios");
        }
    }

    @FXML
    void btnCancerlarOnAction(ActionEvent event) {
        this.close();
    }

    private boolean checkTextFields() {
        Iterator i = text_fields.iterator();
        while(i.hasNext()){
            TextField txft = (TextField) i.next();
            if(txft.getText().isBlank()){
                return false;
            }
            
        }

        return true;
    }

    @Override
    public void close() {
        Stage stage = (Stage) this.btn_clienteCancelar.getScene().getWindow();
        stage.close();

    }

}
