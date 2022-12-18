/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import Utils.Utils;
import aplicacion.LogicLayerException;
import aplicacion.OrderDetailsLogic;
import aplicacion.OrdersLogic;
import aplicacion.modelo.Order;
import aplicacion.modelo.OrderDetails;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Clase controladora para la creación y modificación de líneas de pedidos
 *
 * 
 */
public class CrearModificarLineaPedidosController extends PresentationLayer implements Initializable {

    // ComboBox para seleccionar el código del producto
    @FXML
    private ComboBox<String> comboCodProducto;

    // Campo de edición para la cantidad de productos
    @FXML
    private TextField editCantidad;

    // Campo de edición para el precio de venta del producto
    @FXML
    private TextField editPrecioVenta;
    
    // Objeto OrderDetails que almacena la información de la línea de pedido
    private OrderDetails orderDetails;
    
    /**
     * Método para inicializar el controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Método para obtener los datos desde otra vista.
     */
    public void setData(OrderDetails orderDetails){
        this.orderDetails = orderDetails;
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
     * Método para inicializar la vista con los datos de la línea de pedido.
     */
    private void initView() {
        if(orderDetails == null){
            //TODO: Obtener lista de productos y añadir su nombre a un observablelist y añadirlas al combobox
            //ObservableList customerEmails = FXCollections.observableArrayList();
            //comboCodProducto.setItems();
            return;
        }
        
        editCantidad.setText(String.valueOf(orderDetails.getQuantityOrdered()));
        editPrecioVenta.setText(String.valueOf(orderDetails.getPriceEach()));
        comboCodProducto.setDisable(true);
        //Obtener el nombre del producto basado en la ID
        //comboCodProducto.setValue();
        
    }
    
    /**
     * Método para manejar el evento de clic del botón Aceptar.
     */
    @FXML
    void onActionAccept(ActionEvent event) {
       try {
            this.orderDetailsLogic = new OrderDetailsLogic();
            if(orderDetails == null) orderDetailsLogic.save(constructOrderDetails());
            else orderDetailsLogic.update(constructOrderDetails());
        } catch (LogicLayerException ex) {
            Utils.showErrorAlert("Error: " + ex.getMessage());
        }
    }

    private OrderDetails constructOrderDetails() {
        //Obtener el id del producto basado 
        /*return new OrderDetails(
                0,
                nombreProducto,
                Integer.parseInt(editCantidad.getText()),
                Integer.parseInt(editPrecioVenta.getText()),
                0
        );*/
    }
}
