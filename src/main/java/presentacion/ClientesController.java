/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import aplicacion.modelo.Customer;
import datos.CustomersDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Cole
 */
public class ClientesController extends PresentationLayer implements Initializable {

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

    private ObservableList<Customer> lista = FXCollections.observableArrayList();
    private final CustomersDAO bd = new CustomersDAO();

    public ClientesController() throws SQLException {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colum_clienteNombre.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colum_clienteEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        colum_clienteTelf.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colum_clienteNac.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        colum_clienteTargeta.setCellValueFactory(new PropertyValueFactory<>("idCard"));
        colum_clienteSaldo.setCellValueFactory(new PropertyValueFactory<>("creditLimit"));
        obtenerDatos();
        tbview_cliente.setItems(lista);
    }

    private void obtenerDatos() {
        try {
            lista = (ObservableList<Customer>) bd.getAll();
        } catch (SQLException e) {
            System.out.println("Error al leer los datos: " + e);
        }
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
