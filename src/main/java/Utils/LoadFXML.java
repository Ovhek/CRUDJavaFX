/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import aplicacion.Manager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Función encargada de cargar FXMLS
 * @author Cole
 */
public class LoadFXML {
    
    /**
     Abre una nueva ventana.
     * @param fxml dirección del fxml
     */
    public void openNewWindow(String fxml){
                //Abrir pantalla nueva
        try {
            //Creamos el loader y el controlador para trabajar con la pantalla que abriremos 
            //y traernos parametros
            var test = this.getClass().getClassLoader();
            FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource(fxml));


            //Con root haremos referencia al padre y lo cargaremos
            //Al hacer esto netBeans nos pedira hacer un try catch 
            Parent root = loader.load();
            
            Object tempController = null;
            var managerController = Manager.getInstance().getController(loader.getController().getClass());
            
            if( managerController == null)
                tempController = loader.getController();
            else tempController = managerController;
            
            loader.setController(tempController);
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
}
