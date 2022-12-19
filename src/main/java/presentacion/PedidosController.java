/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import Utils.LoadFXML;
import Utils.Utils;
import aplicacion.LogicLayerException;
import aplicacion.Manager;
import aplicacion.OrderDetailsLogic;
import aplicacion.OrdersLogic;
import aplicacion.modelo.Customer;
import aplicacion.modelo.Order;
import aplicacion.modelo.OrderDetails;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cole
 */
public class PedidosController extends PresentationLayer implements Initializable {

    @FXML
    private Button btnAddLineaPedido;

    @FXML
    private Button btnAddPedido;

    @FXML
    private Button btnDelLineaPedido;

    @FXML
    private Button btnDelPedido;

    @FXML
    private Button btnModLineaPedido;

    @FXML
    private Button btnModPedido;

    @FXML
    private Button btnVolver;

    @FXML
    private HBox hBoxBottomLayout;
        
    @FXML
    private TableColumn columnLineaPedidosCantidad;

    @FXML
    private TableColumn columnLineaPedidosCodigoProducto;

    @FXML
    private TableColumn columnLineaPedidosNombreProducto;

    @FXML
    private TableColumn columnLineaPedidosNumLinea;

    @FXML
    private TableColumn columnLineaPedidosPrecio;

    @FXML
    private TableColumn columnPedidosEmail;

    @FXML
    private TableColumn columnPedidosFechaEnvio;

    @FXML
    private TableColumn columnPedidosFechaPedido;

    @FXML
    private TableColumn columnPedidosFechaRequerida;

    @FXML
    private TableColumn columnPedidosID;

    @FXML
    private HBox hBoxAsVboxContainer;

    @FXML
    private BorderPane responsiveLayout;

    @FXML
    private TableView<OrderDetails> tableLineaPedidos;

    @FXML
    private TableView<Order> tablePedidos;

    @FXML
    private Label txtEmail;

    @FXML
    private Label txtNombre;

    @FXML
    private VBox vBox_1;

    @FXML
    private VBox vBox_2;
    
    @FXML
    private AnchorPane layoutPedidos;
        
    private Customer customer = new Customer();
    private LoadFXML loader = new LoadFXML();
    private Manager manager = Manager.getInstance();
    private float importeTotal = 0f;

    public float getImporteTotal() {
        return importeTotal;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager.getInstance().addController(this);
        initListeners();
    } 
    
    private void initView() {
        try {
            // TODO
            //txtNombre.setText(customer);
            //txtEmail.setText(customer);
            
            this.ordersLogic = new OrdersLogic();
            this.orderDetailsLogic = new OrderDetailsLogic();
            List<Order> orders = ordersLogic.getAllByCustomer(customer);
            ordersLogic.close();
            orders.forEach( order -> {
                try {
                    order.setOrderDetails((ArrayList<OrderDetails>) orderDetailsLogic.getAllByOrderNumber(order));
                    orderDetailsLogic.close();
                } catch (LogicLayerException ex) {
                    Utils.showErrorAlert(ex.getMessage());
                }
            });
            
            ObservableList<Order> observableOrders = FXCollections.observableArrayList(orders);
            
            tablePedidos.setItems(observableOrders);
            columnPedidosEmail.setCellFactory(new PropertyValueFactory("customers_customerEmail"));
            columnPedidosFechaEnvio.setCellFactory(new PropertyValueFactory("shippedDate"));
            columnPedidosFechaPedido.setCellFactory(new PropertyValueFactory("orderDate"));
            columnPedidosFechaRequerida.setCellFactory(new PropertyValueFactory("requiredDate"));
            columnPedidosID.setCellFactory(new PropertyValueFactory("orderNumber"));
            
            
            columnLineaPedidosCantidad.setCellFactory(new PropertyValueFactory("quantityOrdered"));
            columnLineaPedidosCodigoProducto.setCellFactory(new PropertyValueFactory("productCode"));
            //TODO: Obtener el nombre del producto!!
            columnLineaPedidosNombreProducto.setCellFactory(new PropertyValueFactory(""));
            columnLineaPedidosNumLinea.setCellFactory(new PropertyValueFactory("orderLineNumber"));
            columnLineaPedidosPrecio.setCellFactory(new PropertyValueFactory("priceEach"));

           
        } catch (LogicLayerException ex) {
            Utils.showErrorAlert(ex.getMessage());
        }
    }
    
    public void setCustomerData(Customer customer){
        this.customer = customer;
        initView();
    }
    
     /**
     * Inicialización de los listener del controlar
     * 
     */
    private void initListeners() {
        //Función encargada de comprobar la anchura de la ventana y modificar la posición de los objetos en base a ello.
        layoutPedidos.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if(oldScene == null && newScene != null){
                newScene.windowProperty().addListener((ow,oldWindow,newWindow) ->{
                    newWindow.widthProperty().addListener((ov,oldVal,newVal)->{
                        var hboxChildrens = hBoxAsVboxContainer.getChildren();
                        if (newVal.intValue() < 900){
                            hboxChildrens.remove(vBox_2);
                            responsiveLayout.setBottom(vBox_2);
                        }
                        else{
                            responsiveLayout.setBottom(null);

                            if(hboxChildrens.size() != 2)
                                hBoxAsVboxContainer.getChildren().add(vBox_2);

                        }
                    });
                });
            }
        });
    }
    
    @FXML 
    void onActionVolver(ActionEvent event) {

        try {
            FXMLLoader fxmlPrimary = new FXMLLoader(this.getClass().getClassLoader().getResource("presentacion/primary.fxml"));
            Scene scene = new Scene(fxmlPrimary.load());
            fxmlPrimary.setController(Manager.getInstance().getController(PrimaryController.class));
            Stage stage = this.getStage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    void onMouseClickedObtainDetails(MouseEvent event) {
        Order selectedOrder = tablePedidos.getSelectionModel().getSelectedItem();
        ArrayList<OrderDetails> orderDetails = selectedOrder.getOrderDetails();
        orderDetails.forEach(orderDetail -> importeTotal+= orderDetail.getQuantityOrdered()*orderDetail.getPriceEach());
        ObservableList<OrderDetails> observableDetails = FXCollections.observableArrayList(orderDetails);
        tableLineaPedidos.setItems(observableDetails);
    }
    
    @FXML
    void onActionAddLineaPedido(ActionEvent event) {
        openOrdersManager(null);
    }

    @FXML
    void onActionModPedido(ActionEvent event) {
        openOrdersManager(tablePedidos.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    void onActionModLineaPedido(ActionEvent event) {
        openOrderDetailsManager(tableLineaPedidos.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionAddPedido(ActionEvent event) {
        openOrderDetailsManager(null);
    }

    /**
     * Abre la ventana de añadir o editar pedidos.
     * @param order el objeto de tipo order.
     */
    private void openOrdersManager(Order order){
        loader.openNewWindow("/presentacion/pedidosCrearModificar.fxml");
        PedidosCrearModificarController controller = (PedidosCrearModificarController)Manager.getInstance().getController(PedidosCrearModificarController.class);
        controller.setData(order);
    }
    
    /**
     * Abre la ventana de añadir o editar detalles de pedidos.
     * @param orderDetails el objeto de tipo OrderDetails.
     */
    private void openOrderDetailsManager(OrderDetails orderDetails){
        loader.openNewWindow("/presentacion/pedidosCrearModificar.fxml");
        CrearModificarLineaPedidosController controller = (CrearModificarLineaPedidosController)Manager.getInstance().getController(CrearModificarLineaPedidosController.class);
        controller.setData(orderDetails);
    }
    
    @FXML
    void onActionDelLineaPedido(ActionEvent event) {
        try {
            this.orderDetailsLogic = new OrderDetailsLogic();
            orderDetailsLogic.delete(tableLineaPedidos.getSelectionModel().getSelectedItem());
            this.orderDetailsLogic.close();
        } catch (LogicLayerException ex) {
           Utils.showErrorAlert(ex.getMessage());
        }
    }

    @FXML
    void onActionDelPedido(ActionEvent event) {
        try {
            this.ordersLogic = new OrdersLogic();
            ordersLogic.delete(tablePedidos.getSelectionModel().getSelectedItem());
            this.ordersLogic.close();
        } catch (LogicLayerException ex) {
           Utils.showErrorAlert(ex.getMessage());
        }
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
