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
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * Clase controller de la vista crearModificarClientes.fmxl
 */
public class CrearModificarClienteController extends PresentationLayer implements Initializable {
    
    /**
     * Expresion regular que verifica que el saldo se correcto segun
     * el formato 000,000.000 
     */
    private final String CREDIT_REGEX = "^\\d{1,3}(,?\\d{3})*(\\.\\d{1,2})?$";
    /**
     * Expresion regular que verifica que el corre electronico
     * contega al menos un '@' con caracteres a cada lado
     */
    private final String EMAIL_REGEX = "^(.+)@(.+)$";
    /**
     * Expresion regular que verifica que el numero de telefono
     * tenga 9 caracteres numericos
     */
    private final String TELF_REGEX = "^\\d{9}$";

    @FXML
    private Button btn_clienteAceptar;

    @FXML
    private Button btn_clienteCancelar;

    @FXML
    private TextField txtf_clienteEmail;

    @FXML
    private DatePicker dtpick_cliente;

    @FXML
    private TextField txtf_clienteNombre;

    @FXML
    private TextField txtf_clienteSaldo;

    @FXML
    private TextField txtf_clienteTargeta;

    @FXML
    private TextField txtf_clienteTelf;

    private ArrayList<TextField> text_fields;

    /**
     * Añade el controlador al manager para que se accesible desde otras clases
     * Crear una coleccion que contiene los TextFields de la vista
     * Verifica si hay un item selecionado de la tabla clientes, en caso
     * de que un item este seleccionado rellenera los campos con sus datos,
     * en caso contrario rellena los campos con los parametros por defecto
     * ortorgados por la configuracion del programa.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager.getInstance().addController(this);
        text_fields = new ArrayList<>(
                Arrays.asList(
                        txtf_clienteEmail,
                        txtf_clienteNombre,
                        txtf_clienteSaldo,
                        txtf_clienteTargeta,
                        txtf_clienteTelf));

        Customer selectCustomer = ((ClientesController) Manager.getInstance().getController(ClientesController.class)).getSeleccionMode();
        if (selectCustomer != null) {
            autoRelleno(selectCustomer);
            txtf_clienteEmail.setDisable(true);
        } else {
            setDefaultOptions();
        }
    }
    /**
     * Verifica que los valores de todos los campos sean correctos, si los
     * parametros son correctos crea o modifca el cliente seleccionado.
     * @param event 
     */
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
                System.out.println(e);
            } catch (CustomerAgeException e) {
                Utils.Utils.showInfoAlert(e.getMessage());
            }
        }
    }
    
    /**
     * Crea un nuveo cliente y envia a la capa logica, para verificar las
     * reglas de negocio y moficarlo en la base de datos.Tambien actualiza
     * la TableView de clientes.
     * @throws LogicLayerException
     * @throws CustomerAgeException 
     */
    private void modificarCliente() throws LogicLayerException, CustomerAgeException {
        this.customersLogic = new CustomersLogic();
        Customer cliente = newCustomer();
        customersLogic.modificarCliente(cliente);
        ((ClientesController) Manager.getInstance().getController(ClientesController.class)).modificarItem(cliente);

    }
    /**
     * Crea un nuveo cliente y envia a la capa logica, para verificar las
     * reglas de negocio y insertarlo en la base de datos.Tambien actualiza
     * la TableView de clientes.
     * @throws LogicLayerException
     * @throws CustomerAgeException 
     */
    private void crearCliente() throws LogicLayerException, CustomerAgeException {
        this.customersLogic = new CustomersLogic();
        Customer cliente = newCustomer();
        customersLogic.introducirCliente(cliente);
        ((ClientesController) Manager.getInstance().getController(ClientesController.class)).insertItem(cliente);
    }
    /**
     * Rellena todos los campos del formulario con los valores
     * del argumento(Customer)
     * @param customer 
     */
    private void autoRelleno(Customer customer) {
        txtf_clienteEmail.setText(customer.getCustomerEmail());
        txtf_clienteTargeta.setText(customer.getIdCard());
        txtf_clienteNombre.setText(customer.getCustomerName());
        txtf_clienteTelf.setText(customer.getPhone());
        dtpick_cliente.setValue(customer.getBirthDate());
        txtf_clienteSaldo.setText(String.valueOf(customer.getCreditLimit()));
    }
    /**
     * Completa los campos con los valores predeterminados
     */
    private void setDefaultOptions() {

        double defaultCredit = ((AppConfigController) Manager.getInstance().getController(AppConfigController.class)).buildAppConfig().getDefaultCreditLimit();
        dtpick_cliente.setValue(LocalDate.of(2000, Month.MARCH, 22));
        txtf_clienteSaldo.setText(String.valueOf(defaultCredit));
    }
    
    /**
     * Devuelve un nuevo cliente a partir de los valores del formulario
     * @return Customer
     */
    private Customer newCustomer() {
        String customerEmail = txtf_clienteEmail.getText();
        String idCard = txtf_clienteTargeta.getText();
        String customerName = txtf_clienteNombre.getText();
        String phone = txtf_clienteTelf.getText();
        LocalDate birthDate = dtpick_cliente.getValue();
        double credit = Double.parseDouble(txtf_clienteSaldo.getText());
        Customer customer = new Customer(customerEmail, idCard, customerName, phone, credit, birthDate);
        return customer;
    }

    @FXML
    void btnCancerlarOnAction(ActionEvent event) {
        this.close();
    }
    /**
     * Compreuva que los campos sean correctos
     * @return True: si los campos son validos, false si son incorrectos
     */
    private boolean checkTextFields() {
        Iterator i = text_fields.iterator();
        while (i.hasNext()) {
            TextField txft = (TextField) i.next();
            if (txft.getText().isBlank()) {
                Utils.Utils.showInfoAlert("Hay campos vacios");
                return false;
            }

        }

        if (!txtf_clienteEmail.getText().matches(EMAIL_REGEX)) {
            Utils.Utils.showInfoAlert("Email incorrecto");
            return false;
        }
        if (!txtf_clienteTelf.getText().matches(TELF_REGEX)) {
            Utils.Utils.showInfoAlert("Numero de telefono incorrecto");
            return false;
        }
        if (!txtf_clienteSaldo.getText().matches(CREDIT_REGEX)) {
            Utils.Utils.showInfoAlert("Saldo incorrecto");
            return false;
        }

        return true;
    }

    /**
     * Cierra la ventana
     */
    @Override
    public void close() {
        Stage stage = (Stage) this.btn_clienteCancelar.getScene().getWindow();
        stage.close();

    }

}
