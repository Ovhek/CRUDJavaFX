/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacion;

import aplicacion.LogicLayerException;
import aplicacion.Manager;
import aplicacion.ProductsLogic;
import aplicacion.modelo.Product;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bhugo
 */
public class ProductosCrearModificarController extends PresentationLayer implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextArea editDescripcion;

    @FXML
    private TextField editNombre;

    @FXML
    private TextField editPrecio;

    @FXML
    private TextField editStock;

    @FXML
    private Label txtDescripcion;

    @FXML
    private Label txtNombre;

    @FXML
    private Label txtPrecio;

    @FXML
    private Label txtStock;

    private Product data;

    public Product getData() {
        return data;
    }

    public void setData(Product data) {
        this.data = data;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager.getInstance().addController(this);
    }

    @FXML
    void onActionAceptar(ActionEvent event) throws SQLException {
        //Guardamos el contenido de los textField en variables
        String nombreProducto = editNombre.getText();
        String stock = editStock.getText();
        int stockInt = Integer.parseInt(stock);
        String precio = editPrecio.getText();
        float precioFloat = Float.parseFloat(precio);
        String descripcion = editDescripcion.getText();
        
        //asignamos las variables a el constructor de p que no tiene codigo(este se asignara solo)
        Product p = new Product(nombreProducto, stockInt, precioFloat, descripcion);
        
        
        //Comprobamos que no haya campos vacios
        if ((!editNombre.getText().isBlank())
                || (!editStock.getText().isBlank())
                || (!editPrecio.getText().isBlank())
                || (!editDescripcion.getText().isBlank())) {
            //seteamos el producto que hemos guardado en data
            this.data = p;
            try {
                //
                this.productsLogic = new ProductsLogic();
                this.productsLogic.addProducto(p);
            } catch (LogicLayerException ex) {
                Logger.getLogger(ProductosCrearModificarController.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Obtenemos la escena en la que estamos y la cerramos
            Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
            stage.close();
        }
    }

    //Action encargado de cerrar la ventana y cancelar la creacion o modificacion de producto
    @FXML
    public void onActionCancelar(ActionEvent event) {
        //Igualamos el producto a null para que no nos añada nada
        this.data = null;
        // Obtenemos la escena en la que estamos y la cerramos
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
