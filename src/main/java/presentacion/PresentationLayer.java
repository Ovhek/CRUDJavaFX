/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import aplicacion.AppConfigLogic;
import aplicacion.CustomersLogic;
import aplicacion.OrderDetailsLogic;
import aplicacion.OrdersLogic;
import aplicacion.ProductsLogic;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * Generalización de un controlador
 */
public abstract class PresentationLayer {
    AppConfigLogic appConfigLogic;
    CustomersLogic customersLogic;
    OrdersLogic ordersLogic;
    OrderDetailsLogic orderDetailsLogic;
    ProductsLogic productsLogic;
    
    /**
     * Ventana asociada al controller
     */
    private Stage stage;
    
    /**
     * Función la cual devuelve la ventana.
     * @return ventana del controlador
     */
    public Stage getStage(){
        return stage;
    }
    
    /**
     * Función encargada de asignar la ventana al controlador.
     * @param stage ventana
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    /**
     * Muestra un mensaje de error.
     * @param text mensaje a mostrar.
     */
    public void mostrarError(String text){
        Alert a = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
        a.setTitle("ERROR: ");
        a.setContentText(text);
         
        a.show();
    }
    
     /***
     * Permite a las subclases reimplementar el metodo
     * A diferencia de una interfície, esta reimplementación no es
     * obligatoria. 
     * 
     * Reimplementación de cerrar la ventana.
     */
    public abstract void close();
}
