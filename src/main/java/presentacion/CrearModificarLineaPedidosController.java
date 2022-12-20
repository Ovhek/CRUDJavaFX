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
import aplicacion.modelo.AppConfig;
import aplicacion.modelo.Order;
import aplicacion.modelo.OrderDetails;
import aplicacion.modelo.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    @FXML
    private Button btnCancel;

    @FXML
    public void onActionCancel(ActionEvent event) {
        close();
    }

    // Objeto OrderDetails que almacena la información de la línea de pedido
    private OrderDetails orderDetails;
    private ArrayList<Product> products;
    private PedidosController controller;
    AppConfig appConfig;

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
        Stage stage = (Stage) this.btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Método para inicializar la vista con los datos de la línea de pedido.
     */
    private void initView() {
        try {
            appConfig = ((AppConfigController) Manager.getInstance().getController(AppConfigController.class)).buildAppConfig();
            this.productsLogic = new ProductsLogic();
            products = (ArrayList<Product>) this.productsLogic.mostrarProductos();
            if (orderDetails == null) {
                editCantidad.setText(String.valueOf(appConfig.getDefaultQuantityOrdered()));
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
            if (CamposVacios() || !Utils.isNumeric(editCantidad.getText()) || !Utils.isNumeric(editPrecioVenta.getText())) {
                Utils.showInfoAlert("No deben haber campos vacíos o valores no válidos");
                return;
            }
            OrderDetails newOrderDetails = constructOrderDetails();
            this.orderDetailsLogic = new OrderDetailsLogic();
            this.ordersLogic = new OrdersLogic();

            if (orderDetails == null) {
                if (!controller.getObservableDetails().filtered(detail -> detail.getProductCode() == newOrderDetails.getProductCode()).isEmpty()) {
                    Utils.showInfoAlert("Este producto ya se encuentra en la lista.");
                    return;
                }

                if (controller.getObservableDetails().size() + 1 > appConfig.getMaxLinesPerOrder()) {
                    Utils.showInfoAlert(String.format("Has alcanzado el límite máximo de lineas de pedido especificadas en la configuración de la aplicación (%d)", appConfig.getMaxLinesPerOrder()));
                    return;
                }

                checkMaxOrderAmount();
                controller.addItemToOrderDetails(newOrderDetails);
                Order selectedOrder = controller.getSelectedOrder();
                selectedOrder.addOrderDetailsToOrder(newOrderDetails);

                if (selectedOrder.getOrderDetails().size() == 1) {
                    ordersLogic.save(selectedOrder);
                }

                orderDetailsLogic.save(newOrderDetails);

            } else {
                checkMaxOrderAmount();
                orderDetailsLogic.update(newOrderDetails);
                controller.modifyItemOfOrderDetails(orderDetails, newOrderDetails);
            }
            this.orderDetailsLogic.close();
        } catch (LogicLayerException ex) {
            Utils.showErrorAlert("Error: " + ex.getMessage());
        }
    }

    private void checkMaxOrderAmount() {
        if (controller.getImporteTotal()+(Integer.parseInt(editCantidad.getText())*Float.parseFloat(editPrecioVenta.getText()))  >= appConfig.getMaxOrderAmount()) {
            Utils.showInfoAlert(String.format("Has superado el importe máximo para este pedido. el importe máximo es %.0f", appConfig.getMaxOrderAmount()));
            return;
        }
    }

    @FXML
    void onActionComboProductSelected(ActionEvent event) {
        Product selectedProduct = products.stream().filter(product -> product.getProductName().equals(comboCodProducto.getSelectionModel().getSelectedItem())).findFirst().get();
        float precioDeVenta = (selectedProduct.getBuyPrice() + selectedProduct.getBuyPrice() * appConfig.getDefaultProductBanefit() / 100);
        editPrecioVenta.setText(String.valueOf(precioDeVenta));
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

    private boolean CamposVacios() {
        return (comboCodProducto.getSelectionModel().getSelectedItem() == null || editCantidad.getText().isBlank() || editPrecioVenta.getText().isBlank());
    }
}
