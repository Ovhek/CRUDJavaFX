/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import aplicacion.modelo.Customer;
import datos.CustomersDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cole
 */
public class CustomersLogic extends LogicLayer{
    
   

    public CustomersLogic() throws LogicLayerException {
        super();
    }
    
    public List<Customer> obtenerDatos() throws LogicLayerException {
        ArrayList<Customer> lista = new ArrayList<>();
        try {
            lista.addAll(this.getCustomersDAO().getAll());
        } catch (SQLException e) {
            throw new LogicLayerException(e.getMessage());
        }
        return lista;
    }
    

    @Override
    public void close() throws LogicLayerException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
