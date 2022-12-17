/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Clase de utilidades para la aplicación
 * 
 */
public class Utils {
    
    /**
     * Función encargada de mostrar una alerta de error.
     * @param message Mensaje a mostrar.
     */
    public static void showErrorAlert(String message){
        showAlert(AlertType.ERROR, message);
    }
    /**
     * Función encargada de mostrar una alerta de información.
     * @param message Mensaje a mostrar.
     */
    public static void showInfoAlert(String message){
        showAlert(AlertType.INFORMATION, message);
    }
    
    /**
     * Función encargada de mostrar una alerta generica.
     * @param message Mensaje a mostrar.
     * @param type Tipo de alerta.
     */
    private static void showAlert(AlertType type,String message){
        Alert alert = new Alert(type);
        alert.setTitle("Error");
        alert.setHeaderText("Ha ocurrido un error");
        alert.setContentText(message);

        alert.showAndWait();
    }
    
    /**
     * Función que devuelve todos los nodos de un layout (Parent).
     * @return  Todos los nodos del padre.
     */
    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        addAllDescendents(root, nodes);
        return nodes;
    }

    /**
     * Función Recursiva que obtiene los hijos del padre y los añade al arraylist. Si los hijos también tienen hijos se vuelve a llamar a la función.
     */
    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent)
                addAllDescendents((Parent)node, nodes);
        }
    }
   
   /**
    * Función encargada de comprobar si un string es un número.
    * @retun verdadero si es numero, falso si no lo es.
    */
   public static boolean isNumeric(String str) { 
        try {  
          Double.valueOf(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
}
