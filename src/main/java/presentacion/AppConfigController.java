/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import java.net.URL;
import java.util.ResourceBundle;
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
public class AppConfigController implements Initializable {

    @FXML
    private Button btnAplicar;

    @FXML
    private TextField editBeneficioPredetermiando;

    @FXML
    private TextField editCantidadProductosPredeterminado;

    @FXML
    private TextField editEdadMinimaCliente;

    @FXML
    private TextField editHorasMinimasEnvio;

    @FXML
    private TextField editMaxNumeroDemandasPedido;

    @FXML
    private TextField editPrecioMaximoPedido;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        
        /*stage.getScene().getWindow().widthProperty().addListener((ov,oldVal, newVal) ->{
            Node vBoxToMove = vBox_2;
            try{

            } catch (Exception e){
                //Este error se produce por hijos duplicados pero no afecta en si a la aplicación
                System.out.println("Error: " + e.getMessage());
            }
        });*/

    }
    
}
