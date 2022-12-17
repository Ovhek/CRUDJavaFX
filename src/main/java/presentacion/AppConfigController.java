/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import Utils.Utils;
import aplicacion.AppConfigLogic;
import aplicacion.LogicLayerException;
import aplicacion.Manager;
import aplicacion.modelo.AppConfig;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class AppConfigController extends PresentationLayer implements Initializable {

    public String test;
    @FXML
    private Button btnAplicar;

    @FXML
    private TextField editBeneficioPredetermiando;

    @FXML
    private TextField editCantidadProductosPredeterminado;

    @FXML
    public TextField editEdadMinimaCliente;

    @FXML
    private TextField editHorasMinimasEnvio;

    @FXML
    private TextField editMaxNumeroLineasPorPedido;

    @FXML
    private TextField editOrdenesMaximasPedido;

    @FXML
    private TextField editSaldoPredetermiando;

    @FXML
    private Label editSaldoPredeterminado;

    @FXML
    private TextField editStockPredeterminado;

    @FXML
    private AnchorPane layoutAppConfig;
    
    @FXML
    private HBox hBoxAsVboxContainers;
   
    @FXML
    private BorderPane responsiveLayout;

    @FXML
    private VBox vBox_1;

    @FXML
    private VBox vBox_2;
    

    
   
    
    private Stage stage = null;
    private AppConfig appConfig = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager.getInstance().addController(this);
        initView();
        initListeners();
    }    

    /**
     * Inicialización de los listener del controlar
     * 
     */
    private void initListeners() {
        
        //Función encargada de comprobar la anchura de la ventana y modificar la posición de los objetos en base a ello.
        layoutAppConfig.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if(oldScene == null && newScene != null){
                
                var stage = (Stage)newScene.getWindow();
                stage.widthProperty().addListener((ov,oldVal,newVal)->{
                    var hboxChildrens = hBoxAsVboxContainers.getChildren();
                    if (newVal.intValue() < 900){
                        hboxChildrens.remove(vBox_2);
                        responsiveLayout.setBottom(vBox_2);
                    }
                    else{
                        responsiveLayout.setBottom(null);
                        
                        if(hboxChildrens.size() != 2)
                            hBoxAsVboxContainers.getChildren().add(vBox_2);
                        
                    }
                });
            }
        });
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //getters Comunes, obtencion de datos.
    public float getDefaultCreditLimit()      { return Float.parseFloat(editSaldoPredetermiando.getText()); }
    public int   getDefaultQuantityInStock()  { return Integer.parseInt(editStockPredeterminado.getText());}
    public int   getDefaultQuantityOrdered()  { return Integer.parseInt(editCantidadProductosPredeterminado.getText());}
    public int   getDefaultProductBanefit()   { return Integer.parseInt(editBeneficioPredetermiando.getText());}
    public int   getMinShippingHours()        { return Integer.parseInt(editHorasMinimasEnvio.getText());}
    public int   getMinCustomerAge()          { return Integer.parseInt(editEdadMinimaCliente.getText());}
    public int   getMaxLinesPerOrder()        { return Integer.parseInt(editMaxNumeroLineasPorPedido.getText());}
    public float getMaxOrderAmount()          { return Float.parseFloat(editOrdenesMaximasPedido.getText());}
    /**
     * Clase que obtiene el objeto appConfig y rellena los datos de la vista.
     */
    private void initView() {
        try {
            this.appConfigLogic = new AppConfigLogic();
            
            appConfig = appConfigLogic.getAppConfig();
            
            if(appConfig == null) return;
            
            editBeneficioPredetermiando.setText(""+ appConfig.getDefaultProductBanefit());
            editCantidadProductosPredeterminado.setText(""+appConfig.getDefaultQuantityOrdered());
            editEdadMinimaCliente.setText(""+appConfig.getMinCustomerAge());
            editHorasMinimasEnvio.setText(""+appConfig.getMinShippingHours());
            editMaxNumeroLineasPorPedido.setText(""+appConfig.getMaxLinesPerOrder());
            editOrdenesMaximasPedido.setText(""+appConfig.getMaxOrderAmount());
            editSaldoPredetermiando.setText(""+appConfig.getDefaultCreditLimit());
            editStockPredeterminado.setText(""+appConfig.getDefaultQuantityInStock());
        } 
        catch (LogicLayerException ex) {
            Utils.showErrorAlert(ex.getMessage());
        }
    }
    
    /**
        Función que se ejecuta al clicar en el botón de guardar o actualizar el appConfig.
     */
    @FXML
    void onActionAddOrUpdate(ActionEvent event) {
        if(!comprobarTextFields()){
            Utils.showInfoAlert("Se deben completar todos los campos con valores válidos.");
            return;
        }
        try {
            appConfigLogic.delete(appConfig);
            appConfigLogic.save(buildAppConfig());
        } catch (LogicLayerException ex) {
            Utils.showErrorAlert(ex.getMessage());
        }

    }

    /**
        Función encargada de comprobar que todos los textfields están rellenados.
     */
    private boolean comprobarTextFields() {
        boolean todosAsignados = true;
        boolean todosNumeros = true;
        
        //Iterados sobre todos los nodos y buscamos los textfields
        ArrayList<Node> nodos = Utils.getAllNodes(responsiveLayout);
        for(Node nodo : nodos){
             if (nodo instanceof TextField){
                String text = ((TextField)nodo).getText();
                if(text.isBlank()){
                    todosAsignados = false;
                    break;
                }
                if(!Utils.isNumeric(text)){
                    todosNumeros = false;
                    break;
                }
            }
        }
        return todosAsignados && todosNumeros;
    }

    /**
     * Función encargada de construir un objeto de tipo AppConfig
     * @return objeto AppConfig
     */
    private AppConfig buildAppConfig() {
        AppConfig appConfig = new AppConfig();
            
        appConfig.setDefaultCreditLimit(getDefaultCreditLimit());
        appConfig.setDefaultQuantityInStock(getDefaultQuantityInStock());
        appConfig.setDefaultQuantityOrdered(getDefaultQuantityOrdered());
        appConfig.setDefaultProductBanefit(getDefaultProductBanefit());
        appConfig.setMinShippingHours(getMinShippingHours());
        appConfig.setMinCustomerAge(getMinCustomerAge());
        appConfig.setMaxLinesPerOrder(getMaxLinesPerOrder());
        appConfig.setMaxOrderAmount(getMaxOrderAmount());
        return appConfig;
    }
    
}
