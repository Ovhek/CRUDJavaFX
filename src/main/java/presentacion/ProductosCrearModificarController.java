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
import javafx.collections.ObservableList;
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

    //data sera un objeto de clase producto a con la que trabajaremos en el fichero
    private Product data;
    //variable en la que guardaremos el id de el producto a la hora de modificar
    //producto en la base de datos
    private int idProducto;

    //hacemos el get para poder llamar a 
    public Product getData() {
        return data;
    }

    public void autofillProducto() throws LogicLayerException {
        //Controlador para poder trabajar con las funciones de ProductosController
        ProductosController productoController = ((ProductosController) Manager.getInstance().getController(ProductosController.class));
        //Sacamos el producto seleccionado de prodctoController
        Product producto = productoController.getPrductoSeleccionado();
        this.productsLogic = new ProductsLogic();
        int stockDefault = this.productsLogic.verStockDefault();
        if (producto == null) {
            this.idProducto = 0;
            this.editNombre.setText(null);
            this.editStock.setText(Integer.toString(stockDefault));
            this.editPrecio.setText(null);
            this.editDescripcion.setText(null);
        } else {
            this.idProducto = producto.getProductCode();
            this.editNombre.setText(producto.getProductName());
            this.editStock.setText(Integer.toString(producto.getQuantityInStock()));
            this.editPrecio.setText(Float.toString(producto.getBuyPrice()));
            this.editDescripcion.setText(producto.getProductDescription());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //generamos la instancia al ejecutar la pantalla
        Manager.getInstance().addController(this);

        try {
            //Llamamos a la funcion autofill para que compruebe si tiene que
            //rellenar los textField o no
            autofillProducto();
        } catch (LogicLayerException ex) {
            Utils.Utils.showErrorAlert(ex.getMessage());
        }

    }

    @FXML
    void onActionAceptar(ActionEvent event) throws SQLException {

        if (comprobarVacio()) {
            Utils.Utils.showInfoAlert("Hay algun campo en vacio");
        } else {
            //Hacemos un controlador de el producto controller para saber si crear
            //o modificar un producto
            Product pSeleccionado = ((ProductosController) Manager.getInstance().getController(ProductosController.class)).getPrductoSeleccionado();

            if ((editStock.getText().matches("/^([0-9])*$/"))
                    && ((editPrecio.getText().matches("/^([0-9])*$/")))) {
                if (pSeleccionado == null) {
                    crearProducto();
                } else {
                    modificarProducto();
                }
            } else {
                Utils.Utils.showInfoAlert("Stock, o precio no son numeros");
            }
        }

    }

    public boolean comprobarVacio() {

        if ((editNombre.getText() == null)
                || (editStock.getText() == null)
                || (editPrecio.getText() == null)) {
            return true;
        } else {
            return false;
        }

    }

    public void crearProducto() throws SQLException {
        //Guardamos el contenido de los textField en variables
        String nombreProducto = this.editNombre.getText();
        String stock = this.editStock.getText();
        int stockInt = Integer.parseInt(stock);
        String precio = this.editPrecio.getText();
        float precioFloat = Float.parseFloat(precio);
        String descripcion = this.editDescripcion.getText();

        //asignamos las variables a el constructor de p(producto) sin el codigo(este se auto asignara)
        Product p = new Product(nombreProducto, stockInt, precioFloat, descripcion);

        //seteamos el producto que hemos guardado en data
        this.data = p;
        try {
            //Llamamos a productsLogic y creamos el producto que acabamos e escribir en la base de datos
            this.productsLogic = new ProductsLogic();
            this.productsLogic.addProducto(p);
        } catch (LogicLayerException ex) {
            Logger.getLogger(ProductosCrearModificarController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Obtenemos la escena en la que estamos y la cerramos
        Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
        stage.close();

    }

    public void modificarProducto() throws SQLException {
        //Guardamos el contenido de los textField en variables
        String nombreProducto = this.editNombre.getText();
        String stock = this.editStock.getText();
        int stockInt = Integer.parseInt(stock);
        String precio = this.editPrecio.getText();
        float precioFloat = Float.parseFloat(precio);
        String descripcion = this.editDescripcion.getText();

        //asignamos las variables a el constructor de p(producto) sin el codigo(este se auto asignara)
        Product p = new Product(this.idProducto, nombreProducto, descripcion, stockInt, precioFloat);
        //Comprobamos que no haya campos vacios

        //seteamos el producto que hemos guardado en data
        this.data = p;
        try {
            //Llamamos a productsLogic y actualizamos el producto que acabamos e escribir en la base de datos
            this.productsLogic = new ProductsLogic();
            this.productsLogic.updateProduto(p);
        } catch (LogicLayerException ex) {
            Utils.Utils.showErrorAlert(ex.getMessage());
            System.out.println(ex.getMessage());
        }
        //Obtenemos la escena en la que estamos y la cerramos
        Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
        stage.close();

    }

    //Action encargado de cerrar la ventana y cancelar la creacion o modificacion de producto
    @FXML
    public void onActionCancelar(ActionEvent event) {
        //Igualamos el producto a null para que no nos añada nada
        this.data = null;
        //Obtenemos la escena en la que estamos y la cerramos
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
