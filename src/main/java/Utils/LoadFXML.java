/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Cole
 */
public class LoadFXML {
    
    public void openNewWindow(String fxml){
                //Abrir pantalla nueva
        try {
            //Creamos el loader y el controlador para trabajar con la pantalla que abriremos 
            //y traernos parametros
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxml));

            //Con root haremos referencia al padre y lo cargaremos
            //Al hacer esto netBeans nos pedira hacer un try catch 
            Parent root = loader.load();
            
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
