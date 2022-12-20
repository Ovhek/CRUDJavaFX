/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import aplicacion.CustomerAgeException;
import aplicacion.CustomersLogic;
import aplicacion.LogicLayerException;
import aplicacion.Manager;
import aplicacion.modelo.AppConfig;
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
import javafx.scene.control.TableView;
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

        Customer selectCustomer = ((ClientesController) Manager.getInstance().getController(ClientesController.class)).getSeleccionMode();
        if (selectCustomer != null) {
            autoRelleno(selectCustomer);
            txtf_clienteEmail.setDisable(true);
        }else{
            setDefaultOptions();
        }
    }

    @FXML
    void btnAceptarOnAction(ActionEvent event) {
        if (checkTextFields()) {
            try {

                Customer selection = ((ClientesController) Manager.getInstance().getController(ClientesController.class)).getSeleccionMode();
                if (selection == null) {
                    crearCliente();
                } else {
                    modificarCliente();
                }

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

    private void modificarCliente() throws LogicLayerException, CustomerAgeException {
        this.customersLogic = new CustomersLogic();
        Customer cliente = newCustomer();
        customersLogic.modificarCliente(cliente);
        ((ClientesController) Manager.getInstance().getController(ClientesController.class)).modificarItem(cliente);

    }

    private void crearCliente() throws LogicLayerException, CustomerAgeException {
        this.customersLogic = new CustomersLogic();
        Customer cliente = newCustomer();
        customersLogic.introducirCliente(cliente);
        ((ClientesController) Manager.getInstance().getController(ClientesController.class)).insertItem(cliente);
    }

    private void autoRelleno(Customer customer) {
        txtf_clienteEmail.setText(customer.getCustomerEmail());
        txtf_clienteTargeta.setText(customer.getIdCard());
        txtf_clienteNombre.setText(customer.getCustomerName());
        txtf_clienteTelf.setText(customer.getPhone());
        txtf_clienteNac.setText(customer.getBirthDate().toString());
        txtf_clienteSaldo.setText(String.valueOf(customer.getCreditLimit()));
    }

    private void setDefaultOptions() {

        double defaultCredit = ((AppConfigController) Manager.getInstance().getController(AppConfigController.class)).buildAppConfig().getDefaultCreditLimit();

        txtf_clienteSaldo.setText(String.valueOf(defaultCredit));
    }

    private Customer newCustomer() {
        String customerEmail = txtf_clienteEmail.getText();
        String idCard = txtf_clienteTargeta.getText();
        String customerName = txtf_clienteNombre.getText();
        String phone = txtf_clienteTelf.getText();
        LocalDate birthDate = Utils.Utils.stringToDate(txtf_clienteNac.getText());
        double credit = Double.parseDouble(txtf_clienteSaldo.getText());
        Customer customer = new Customer(customerEmail, idCard, customerName, phone, credit, birthDate);
        return customer;
    }

    @FXML
    void btnCancerlarOnAction(ActionEvent event) {
        this.close();
    }

    private boolean checkTextFields() {
        Iterator i = text_fields.iterator();
        while (i.hasNext()) {
            TextField txft = (TextField) i.next();
            if (txft.getText().isBlank()) {
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
