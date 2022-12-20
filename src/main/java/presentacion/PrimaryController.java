package presentacion;

import aplicacion.App;
import aplicacion.Manager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javax.naming.ldap.ManageReferralControl;

public class PrimaryController extends PresentationLayer implements Initializable{
    @Override
    public void close() {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager.getInstance().addController(this);
        //addAllControllers();

    }

    private void addAllControllers() {
       
    }
    
    /**
     Obtiene el controlador del fxml y lo añadele al singleton
     * @param fxml Archivo fxml
     */
    private void addController(String fxml){
        Manager manager = Manager.getInstance();
        manager.addController(this);
       
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.load(getClass().getResource(fxml).openStream());
            PresentationLayer controller = (PresentationLayer) fxmlLoader.getController();
            manager.addController(controller);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
