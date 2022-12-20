/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import Utils.Utils;
import aplicacion.LogicLayerException;
import aplicacion.Manager;
import aplicacion.OrdersLogic;
import aplicacion.modelo.Order;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * 
 */
public class PedidosCrearModificarController extends PresentationLayer implements Initializable {


    // ComboBox del email
    @FXML
    private ComboBox<String > comboEmailCliente;
    // Botón para aceptar los cambios
    @FXML
    private Button btnAceptar;

    // Botón para cancelar los cambios
    @FXML
    private Button btnCancelar;

    // Campo de selección de fecha para la fecha de envío del pedido
    @FXML
    private DatePicker dateFechaEnvio;

    // Campo de selección de fecha para la fecha de llegada del pedido
    @FXML
    private DatePicker dateFechaLlegada;

    // Campo de selección de fecha para la fecha de pedido
    @FXML
    private DatePicker dateFechaPedido;

    // Campo de edición de correo electrónico del cliente
    @FXML
    private TextField editEmailCliente;

    // Etiqueta para mostrar el correo electrónico del cliente
    @FXML
    private Label txtEmailCliente;

    // Etiqueta para mostrar la fecha de envío del pedido
    @FXML
    private Label txtFEnvio;

    // Etiqueta para mostrar la fecha de llegada del pedido
    @FXML
    private Label txtFLlegada;

    // Etiqueta para mostrar la fecha de pedido
    @FXML
    private Label txtFPedido;
    
    // Objeto Order que almacena la información del pedido
    private Order order;
    
    /**
     * Método para inicializar el controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager.getInstance().addController(this);
    }    

    /**
     * Método para obtener los datos desde otra vista.
     */
    public void setData(Order order){
        this.order = order;
        initView();
    }
    
    /**
     * Método para cerrar la vista.
     */
    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Método para inicializar la vista con los datos del pedido.
     */
    private void initView() {
        if(order == null){
            //TODO: Obtener lista de customers
            //ObservableList customerEmails = FXCollections.observableArrayList();
            //añadirla al combobox
            //comboEmailCliente.setItems();
            //this.customersLogic.close();
            return;
        }

        // Establecer la fecha de envío del pedido en el campo de selección de fecha
        dateFechaEnvio.setValue(order.getShippedDate().toLocalDateTime().toLocalDate());
        // Establecer la fecha de llegada del pedido en el campo de selección de fecha
        dateFechaLlegada.setValue(order.getRequiredDate().toLocalDateTime().toLocalDate());
        // Establecer la fecha de pedido en el campo de selección de fecha
        dateFechaPedido.setValue(order.getOrderDate().toLocalDateTime().toLocalDate());
        // Establecer el correo electrónico del cliente en el campo de edición
        comboEmailCliente.setDisable(true);
        comboEmailCliente.setValue(order.getCustomers_customerEmail());
    }
    
    /**
     * Método para manejar el evento de clic del botón Aceptar.
     */
    @FXML
    void onActionAceptar(ActionEvent event) {
        try {
            this.ordersLogic = new OrdersLogic();
            if(order == null) ordersLogic.save(constructOrder());
            else ordersLogic.update(constructOrder());
            this.ordersLogic.close();
        } catch (LogicLayerException ex) {
            Utils.showErrorAlert("Error: " + ex.getMessage());
        }
    }

    /**
     * * Construye un objeto Order
    */
    private Order constructOrder() {
        return new Order(
                0,
                obtainTimeStamp(dateFechaPedido.getValue()),
                obtainTimeStamp(dateFechaLlegada.getValue()),
                obtainTimeStamp(dateFechaEnvio.getValue()),
                comboEmailCliente.getSelectionModel().getSelectedItem()
        );
    }
    
    /**
     * Conversión a TimeStamp
     */
    private Timestamp obtainTimeStamp(LocalDate date){
        return Timestamp.from(date.atStartOfDay().toInstant(ZoneOffset.UTC));
    }
    
}
