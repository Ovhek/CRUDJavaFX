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
import aplicacion.ProductsLogic;
import aplicacion.modelo.Customer;
import aplicacion.modelo.Order;
import aplicacion.modelo.OrderDetails;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
    private TableColumn<OrderDetails, String> columnLineaPedidosNombreProducto;

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
    private Button btnRemoveFilter;
    
    @FXML
    private DatePicker dateFechaFin;

    @FXML
    private DatePicker dateFechaInicio;
    
    @FXML
    private AnchorPane layoutPedidos;
    
    @FXML
    private Label txtImporteTotal;
    

    private Customer customer = new Customer();
    private LoadFXML loader = new LoadFXML();
    private Manager manager = Manager.getInstance();
    private float importeTotal = 0f;
    private Order selectedOrder;
    private OrderDetails selectedOrderDetails;
    private ObservableList<OrderDetails> observableDetails;
    private ObservableList<Order> observableOrders;
    private String customerEmail;

    public ObservableList<Order> getObservableOrders() {
        return observableOrders;
    }

    public ObservableList<OrderDetails> getObservableDetails() {
        return observableDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public OrderDetails getSelectedOrderDetails() {
        return selectedOrderDetails;
    }

    public float getImporteTotal() {
        return importeTotal;
    }


        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager.getInstance().addController(this);
        initView();
        initListeners();
    }

    private void initView() {
        try {
            btnModLineaPedido.setDisable(true);
            btnModPedido.setDisable(true);
            btnDelPedido.setDisable(true);
            btnDelLineaPedido.setDisable(true);
            btnRemoveFilter.setDisable(true);
            
            customer = ((ClientesController) Manager.getInstance().getController(ClientesController.class)).getSeleccionMode();
            customerEmail = customer.getCustomerEmail();
            txtNombre.setText(customer.getCustomerName());
            txtEmail.setText(customerEmail);

            this.ordersLogic = new OrdersLogic();
            this.orderDetailsLogic = new OrderDetailsLogic();
            List<Order> orders = ordersLogic.getAllByCustomer(customer);
            ordersLogic.close();
            orders.forEach(order -> {
                try {
                    order.setOrderDetails((ArrayList<OrderDetails>) orderDetailsLogic.getAllByOrderNumber(order));
                } catch (LogicLayerException ex) {
                    Utils.showErrorAlert(ex.getMessage());
                }
            });
            orderDetailsLogic.close();
            observableOrders = FXCollections.observableArrayList(orders);

            tablePedidos.setItems(observableOrders);
            columnPedidosEmail.setCellValueFactory(new PropertyValueFactory("customers_customerEmail"));
            columnPedidosFechaEnvio.setCellValueFactory(new PropertyValueFactory("shippedDate"));
            columnPedidosFechaPedido.setCellValueFactory(new PropertyValueFactory("orderDate"));
            columnPedidosFechaRequerida.setCellValueFactory(new PropertyValueFactory("requiredDate"));
            columnPedidosID.setCellValueFactory(new PropertyValueFactory("orderNumber"));

            columnLineaPedidosCantidad.setCellValueFactory(new PropertyValueFactory("quantityOrdered"));
            columnLineaPedidosCodigoProducto.setCellValueFactory(new PropertyValueFactory("productCode"));
            columnLineaPedidosPrecio.setCellValueFactory(new PropertyValueFactory("priceEach"));

            this.productsLogic = new ProductsLogic();
            //Mostramos el nombre del Producto utilizando una customización del tablecolumn.
            columnLineaPedidosNombreProducto.setCellValueFactory(cellData -> {
                SimpleStringProperty ret = null;
                try {
                    ret = new SimpleStringProperty(
                            this.productsLogic.getProductoByProductCode(
                                    String.valueOf(cellData.getValue().getProductCode())
                            ).getProductName()
                    );
                } catch (LogicLayerException ex) {
                    Utils.showErrorAlert(ex.getMessage());
                }
                return ret;
            });
            
            this.productsLogic.close();

        } catch (LogicLayerException ex) {
            Utils.showErrorAlert(ex.getMessage());
        }
    }

    /**
     * Inicialización de los listener del controlar
     *
     */
    private void initListeners() {
        tablePedidos.setOnMouseClicked( e ->{ onMouseClickedPedidos(e); });
        tableLineaPedidos.setOnMouseClicked( e ->{ onMouseClickedLineaPedidos(e); });
        //Función encargada de comprobar la anchura de la ventana y modificar la posición de los objetos en base a ello.
        layoutPedidos.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                newScene.windowProperty().addListener((ow, oldWindow, newWindow) -> {
                    if (newWindow == null) {
                        return;
                    }
                    newWindow.widthProperty().addListener((ov, oldVal, newVal) -> {
                        var hboxChildrens = hBoxAsVboxContainer.getChildren();
                        if (newVal.intValue() < 950) {
                            hboxChildrens.remove(vBox_2);
                            responsiveLayout.setBottom(vBox_2);
                        } else {
                            responsiveLayout.setBottom(null);

                            if (hboxChildrens.size() != 2) {
                                hBoxAsVboxContainer.getChildren().add(vBox_2);
                            }

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
            Stage stage = (Stage) tablePedidos.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void onActionAddLineaPedido(ActionEvent event) {
        selectedOrderDetails = null;
        openOrderDetailsManager();
    }

    @FXML
    void onActionModPedido(ActionEvent event) {
        selectedOrder = tablePedidos.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            return;
        }
        openOrdersManager();
    }

    @FXML
    void onActionModLineaPedido(ActionEvent event) {
        selectedOrderDetails = tableLineaPedidos.getSelectionModel().getSelectedItem();
        if (selectedOrderDetails == null) {
            return;
        }
        openOrderDetailsManager();
    }

    @FXML
    void onActionAddPedido(ActionEvent event) {
        selectedOrder = null;
        openOrdersManager();
    }

    /**
     * Abre la ventana de añadir o editar pedidos.
     *
     * @param order el objeto de tipo order.
     */
    private void openOrdersManager() {
        loader.openNewWindow("presentacion/pedidosCrearModificar.fxml");
    }

    /**
     * Abre la ventana de añadir o editar detalles de pedidos.
     *
     * @param orderDetails el objeto de tipo OrderDetails.
     */
    private void openOrderDetailsManager() {
        loader.openNewWindow("presentacion/crearModificarLineaPedidos.fxml");
    }

    @FXML
    void onActionDelLineaPedido(ActionEvent event) {
        selectedOrderDetails = tableLineaPedidos.getSelectionModel().getSelectedItem();
        if (selectedOrderDetails == null) {
            return;
        }
        try {
            this.orderDetailsLogic = new OrderDetailsLogic();
            orderDetailsLogic.delete(selectedOrderDetails);
            observableDetails.remove(selectedOrderDetails);
            tableLineaPedidos.refresh();
            this.orderDetailsLogic.close();
        } catch (LogicLayerException ex) {
            Utils.showErrorAlert(ex.getMessage());
        }
    }

    public void addItemToOrders(Order order) {
        observableOrders.add(order);
        tablePedidos.refresh();
    }

    public void modifyItemOfOrders(Order oldOrder, Order newOrder) {
        observableOrders.set(observableOrders.indexOf(oldOrder), newOrder);
        tablePedidos.setItems(observableOrders);
    }

    public void addItemToOrderDetails(OrderDetails orderDetails) {
        observableDetails.add(orderDetails);
        tableLineaPedidos.refresh();
        calcularImporteFinal((ArrayList<OrderDetails>) observableDetails.stream().collect(Collectors.toList()));
    }

    public void modifyItemOfOrderDetails(OrderDetails oldOrderDetails, OrderDetails newOrderDetails) {
        observableDetails.set(observableDetails.indexOf(oldOrderDetails), newOrderDetails);
        tableLineaPedidos.refresh();
    }

    @FXML
    void onActionDelPedido(ActionEvent event) {
        selectedOrder = tablePedidos.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            return;
        }
        try {
            this.ordersLogic = new OrdersLogic();
            ordersLogic.delete(selectedOrder);
            observableOrders.remove(selectedOrder);
            tablePedidos.refresh();
            this.ordersLogic.close();
        } catch (LogicLayerException ex) {
            Utils.showErrorAlert(ex.getMessage());
        }
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean dontExecute = false;
    @FXML
    void onActionDateFechaFin(ActionEvent event) {
        if(dontExecute) return;
        
        if(dateFechaInicio.getValue() == null) return;
        btnRemoveFilter.setDisable(false);
        filtrarPedidos();
    }

    @FXML
    void onActionDateFechaInicio(ActionEvent event) {
        if(dontExecute) return;
        if(dateFechaFin.getValue() == null) return;
        btnRemoveFilter.setDisable(false);
        filtrarPedidos();
    }
    
    @FXML
    void onActionRemoveFilter(ActionEvent event) {
        dontExecute = true;
        dateFechaFin.setValue(null);
        dateFechaInicio.setValue(null);
        tablePedidos.setItems(observableOrders);
        dontExecute = false;
    }
    
    private void filtrarPedidos() {
        ArrayList<Order> filteredObservableList = (ArrayList<Order>) observableOrders
                .stream()
                .filter(order -> 
                        order.getOrderDate().after(datePickerToTimestamp(dateFechaInicio.getValue())) && 
                        order.getOrderDate().before(datePickerToTimestamp(dateFechaFin.getValue()))
                ).collect(Collectors.toList());
                
        tablePedidos.setItems(FXCollections.observableArrayList(filteredObservableList));
    }
    
    private Timestamp datePickerToTimestamp(LocalDate date){
        return Timestamp.from(date.atStartOfDay().toInstant(ZoneOffset.UTC));
    }
    
    private void onMouseClickedPedidos(MouseEvent e) {
        selectedOrder = tablePedidos.getSelectionModel().getSelectedItem();
        observableDetails = FXCollections.observableArrayList();
        if (selectedOrder == null) {
            return;
        }
        btnModPedido.setDisable(false);
        btnDelPedido.setDisable(false);
        ArrayList<OrderDetails> orderDetails = selectedOrder.getOrderDetails();
        if (orderDetails.size() == 0) {
            btnDelLineaPedido.setDisable(true);
            btnModLineaPedido.setDisable(true);
        }
        calcularImporteFinal(orderDetails);
        observableDetails = FXCollections.observableArrayList(orderDetails);
        tableLineaPedidos.setItems(observableDetails);
    }
    
    private void onMouseClickedLineaPedidos(MouseEvent e) {
        selectedOrderDetails = tableLineaPedidos.getSelectionModel().getSelectedItem();
        if (selectedOrderDetails == null) {
            return;
        }
        btnDelLineaPedido.setDisable(false);
        btnModLineaPedido.setDisable(false);
    }
    private void calcularImporteFinal(ArrayList<OrderDetails> orderDetails){
        importeTotal = 0f;
        orderDetails.forEach(orderDetail -> importeTotal += orderDetail.getQuantityOrdered() * orderDetail.getPriceEach());
        txtImporteTotal.setText(String.format("%.2f €", importeTotal)) ;
    }
}
