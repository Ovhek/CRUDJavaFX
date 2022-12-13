package aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import presentacion.PrimaryController;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Manager manager;
    private static Stage stage;
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        manager = Manager.getInstance();
        
        // Ventana Principal
        FXMLLoader fxmlPrimary = loadFXML("primary");
        scene = new Scene(fxmlPrimary.load());
        stage.setScene(scene);
        stage.show();
    }

    private void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }

    private FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getClassLoader().getResource("presentacion/"+fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }
    
    /**
     * Sobreescribimos el metodo stop para cerrar los controladores.
     */
    @Override
    public void stop() {
        
        //TODO: Implementar.
        //tanquem controllers
        //OficinesPresentation oc = (OficinesPresentation)App.getManager().getController(OficinesPresentation.class);
        //oc.close();
        
        System.exit(0);
        
    }

}