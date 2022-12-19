/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import Utils.LoadFXML;
import aplicacion.CustomersLogic;
import aplicacion.LogicLayerException;
import aplicacion.modelo.Customer;
import datos.CustomersDAO;
import java.net.URL;
import java.sql.SQLException;
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
    private TableColumn<Customer, String> colum_clienteNac;
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
        loadFXML.openNewWindow("/presentacion/crearModificarCliente.fxml");
    }
    @FXML
    void btnPedidosOnAction(ActionEvent event) {

    }

    private void cargarDatos() {
        try {
            lista.addAll(customersLogic.obtenerDatos());
        } catch (LogicLayerException ex) {
            ex.getMessage();
        }
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
