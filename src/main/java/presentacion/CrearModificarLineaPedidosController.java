/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import Utils.Utils;
import aplicacion.LogicLayerException;
import aplicacion.Manager;
import aplicacion.OrderDetailsLogic;
import aplicacion.OrdersLogic;
import aplicacion.ProductsLogic;
import aplicacion.modelo.Order;
import aplicacion.modelo.OrderDetails;
import aplicacion.modelo.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ArrayList<Product> products;
    private PedidosController controller;
    /**
     * Método para inicializar el controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager.getInstance().addController(this);
        controller = ((PedidosController) Manager.getInstance().getController(PedidosController.class));
        this.orderDetails = controller.getSelectedOrderDetails();
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
        try {
            this.productsLogic = new ProductsLogic();
            products = (ArrayList<Product>) this.productsLogic.mostrarProductos();
            if (orderDetails == null) {
                editCantidad.setText("");
                editPrecioVenta.setText("");
                
                List<String> productNames = products.stream().map(product -> product.getProductName()).collect(Collectors.toList());
                
                ObservableList observableProductsNames = FXCollections.observableArrayList(productNames);
               comboCodProducto.setItems(observableProductsNames);
                this.productsLogic.close();

                return;
            }

            editCantidad.setText(String.valueOf(orderDetails.getQuantityOrdered()));
            editPrecioVenta.setText(String.valueOf(orderDetails.getPriceEach()));
            
            //Obtener el nombre del producto basado en la ID
            
            String name = products.stream()
                                    .filter(product -> product.getProductCode() == orderDetails.getProductCode())
                                    .findFirst()
                                    .get()
                                    .getProductName();
            
            comboCodProducto.getItems().add(name);
            comboCodProducto.getSelectionModel().selectFirst();
            comboCodProducto.setDisable(true);
        } catch (LogicLayerException ex) {
            Utils.showErrorAlert(ex.getMessage());
        }

    }

    /**
     * Método para manejar el evento de clic del botón Aceptar.
     */
    @FXML
    void onActionAccept(ActionEvent event) {
        try {
            OrderDetails newOrderDetails = constructOrderDetails();
            this.orderDetailsLogic = new OrderDetailsLogic();
            this.ordersLogic = new OrdersLogic();
            
            if (orderDetails == null) {
                controller.addItemToOrderDetails(newOrderDetails);
                Order selectedOrder = controller.getSelectedOrder();
                selectedOrder.addOrderDetailsToOrder(newOrderDetails);
                
                if(selectedOrder.getOrderDetails().size() == 1) ordersLogic.save(selectedOrder);
                orderDetailsLogic.save(newOrderDetails);
                

                    
            } else {
                orderDetailsLogic.update(newOrderDetails);
                controller.modifyItemOfOrderDetails(orderDetails, newOrderDetails);
            }
            this.orderDetailsLogic.close();
        } catch (LogicLayerException ex) {
            Utils.showErrorAlert("Error: " + ex.getMessage());
        }
    }

    private OrderDetails constructOrderDetails() {
        
        int productCode = products.stream().filter(product -> product.getProductName().equals(comboCodProducto.getSelectionModel().getSelectedItem()))
                                    .findFirst()
                                    .get()
                                    .getProductCode();
        return new OrderDetails(
                controller.getSelectedOrder().getOrderNumber(),
                productCode,
                Integer.parseInt(editCantidad.getText()),
                Float.parseFloat(editPrecioVenta.getText()),
                0
        );
    }
}
