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

public class PrimaryController extends PresentationLayer implements Initializable {

    @Override
    public void close() {

    }

    /**
     * Método que se ejecuta al inicializar el controlador.
     * @param url URL del recurso.
     * @param rb Bundle de recursos.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager.getInstance().addController(this);
    }

}
