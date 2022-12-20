/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import Utils.LoadFXML;
import aplicacion.CustomersLogic;
import aplicacion.LogicLayerException;
import aplicacion.Manager;
import aplicacion.modelo.Customer;
import datos.CustomersDAO;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Cole
 */
public class ClientesController extends PresentationLayer implements Initializable {

    private LoadFXML loadFXML = new LoadFXML();

    @FXML
    private TableView<Customer> tbview_cliente;
    @FXML
    private TableColumn<Customer, String> colum_clienteNombre;
    @FXML
    private TableColumn<Customer, String> colum_clienteEmail;
    @FXML
    private TableColumn<Customer, String> colum_clienteTelf;
    @FXML
    private TableColumn<Customer, LocalDate> colum_clienteNac;
    @FXML
    private TableColumn<Customer, String> colum_clienteTargeta;
    @FXML
    private TableColumn<Customer, Double> colum_clienteSaldo;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_eliminar;
    @FXML
    private Button btn_modificar;
    @FXML
    private Button btn_pedidos;

    private ObservableList<Customer> lista = FXCollections.observableArrayList();

    private CustomersLogic customersLogic;

    public ClientesController() {
        Manager.getInstance().addController(this);
        try {
            this.customersLogic = new CustomersLogic();
        } catch (LogicLayerException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colum_clienteNombre.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colum_clienteEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        colum_clienteTelf.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colum_clienteNac.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        colum_clienteTargeta.setCellValueFactory(new PropertyValueFactory<>("idCard"));
        colum_clienteSaldo.setCellValueFactory(new PropertyValueFactory<>("creditLimit"));
        cargarDatos();
        tbview_cliente.setItems(lista);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        deselect();
        loadFXML.openNewWindow("/presentacion/crearModificarCliente.fxml");
    }

    @FXML
    void btnEliminarOnAction(ActionEvent event) {
        int index = tbview_cliente.getSelectionModel().getSelectedIndex();
        Customer cliente = tbview_cliente.getSelectionModel().getSelectedItem();

        if (cliente != null) {
            try {
                customersLogic.eliminarCliente(cliente);
                lista.remove(index);
                tbview_cliente.refresh();
            } catch (LogicLayerException e) {
                Utils.Utils.showErrorAlert("Error al elimnar cliente de la base de datos: " + e.getMessage());
                System.out.println(e.getMessage());
            }

        }
    }

    @FXML
    void btnModificarOnAction(ActionEvent event) {
        if (this.getSeleccionMode() != null) {
            loadFXML.openNewWindow("/presentacion/crearModificarCliente.fxml");
        }
    }

    @FXML
    void btnPedidosOnAction(ActionEvent event) {

    }
    

    public void modificarItem(Customer customer) {
        Customer cliente = lista.get(tbview_cliente.getSelectionModel().getSelectedIndex());
        cliente.setBirthDate(customer.getBirthDate());
        cliente.setCreditLimit(customer.getCreditLimit());
        cliente.setCustomerEmail(customer.getCustomerEmail());
        cliente.setCustomerName(customer.getCustomerName());
        cliente.setIdCard(customer.getIdCard());
        cliente.setPhone(customer.getPhone());
        tbview_cliente.refresh();
    }

    public void insertItem(Customer customer) {
        lista.add(customer);
        tbview_cliente.refresh();
    }

    private void cargarDatos() {
        try {
            lista.addAll(customersLogic.obtenerDatos());
        } catch (LogicLayerException ex) {
            ex.getMessage();
        }
    }

    private void deselect() {
        tbview_cliente.getSelectionModel().clearSelection();
    }

    public Customer getSeleccionMode() {
        return tbview_cliente.getSelectionModel().getSelectedItem();
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
