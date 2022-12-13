package presentacion;

import aplicacion.App;
import aplicacion.Manager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.naming.ldap.ManageReferralControl;

public class PrimaryController extends PresentationLayer implements Initializable{
    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager manager = Manager.getInstance();
        manager.addController(this);
        manager.addController(this);
        manager.addController(this);

    }

}
