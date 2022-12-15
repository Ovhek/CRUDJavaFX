/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cole
 */
public class ProductosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnAñadir;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<?, ?> columnCodigoPr;

    @FXML
    private TableColumn<?, ?> columnDescripcionPr;

    @FXML
    private TableColumn<?, ?> columnNombrePr;

    @FXML
    private TableColumn<?, ?> columnPrecioPr;

    @FXML
    private TableColumn<?, ?> columnStockPr;

    @FXML
    private TableView<?> tblProductos;
    @FXML
    private VBox vBoxProductos;
    @FXML
    private HBox hBoxProductos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void onActionAdd(ActionEvent event) {
        //Abrir pantalla nueva
        try {
            //Creamos el loader y el controlador para trabajar con la pantalla que abriremos 
            //y traernos parametros
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/presentacion/productosCrearModificar.fxml"));

            //Con root haremos referencia al padre y lo cargaremos
            //Al hacer esto netBeans nos pedira hacer un try catch 
            Parent root = loader.load();

            //crearemos el controlador y con el loader cogeremos el controlador de una vista en concreto
            ProductosCrearModificarController controlador = loader.getController();

            //Creams scene y stage para trabajar con otra pantalla
            //creamos una escena que vendra del padre
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //el dialogo lo hacemos modal(significa que cuando yo lo habra, hasta que yo no termine con el no me deje)
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void onActionModificar(ActionEvent event) {
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
    }
}
