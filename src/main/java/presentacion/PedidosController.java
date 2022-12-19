/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import Utils.LoadFXML;
import aplicacion.Manager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableColumn<?, ?> columnLineaPedidosCantidad;

    @FXML
    private TableColumn<?, ?> columnLineaPedidosCodigoProducto;

    @FXML
    private TableColumn<?, ?> columnLineaPedidosNombreProducto;

    @FXML
    private TableColumn<?, ?> columnLineaPedidosNumLinea;

    @FXML
    private TableColumn<?, ?> columnLineaPedidosNumeroPedido;

    @FXML
    private TableColumn<?, ?> columnLineaPedidosPrecio;

    @FXML
    private TableColumn<?, ?> columnPedidosEmail;

    @FXML
    private TableColumn<?, ?> columnPedidosFechaEnvio;

    @FXML
    private TableColumn<?, ?> columnPedidosFechaPedido;

    @FXML
    private TableColumn<?, ?> columnPedidosFechaRequerida;

    @FXML
    private TableColumn<?, ?> columnPedidosID;

    @FXML
    private HBox hBoxAsVboxContainer;

    @FXML
    private BorderPane responsiveLayout;

    @FXML
    private TableView<?> tableLineaPedidos;

    @FXML
    private TableView<?> tablePedidos;

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
        
    private LoadFXML loader = new LoadFXML();
    private Manager manager = Manager.getInstance();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initListeners();
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
    void onActionAddLineaPedido(ActionEvent event) {
        loader.openNewWindow("/presentacion/crearModificarLineaPedidos.fxml");
    }

    @FXML
    void onActionAddPedido(ActionEvent event) {
        loader.openNewWindow("/presentacion/pedidosCrearModificar.fxml");
    }

    @FXML
    void onActionDelLineaPedido(ActionEvent event) {

    }

    @FXML
    void onActionDelPedido(ActionEvent event) {

    }

    @FXML
    void onActionModLineaPedido(ActionEvent event) {
        loader.openNewWindow("/presentacion/crearModificarLineaPedidos.fxml");
    }

    @FXML
    void onActionModPedido(ActionEvent event) {
        loader.openNewWindow("/presentacion/pedidosCrearModificar.fxml");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
