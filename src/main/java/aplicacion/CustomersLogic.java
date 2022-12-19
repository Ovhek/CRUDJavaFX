/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import aplicacion.modelo.AppConfig;
import aplicacion.modelo.Customer;
import datos.CustomersDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import presentacion.AppConfigController;

/**
 *
 * @author Cole
 */
public class CustomersLogic extends LogicLayer {

    public CustomersLogic() throws LogicLayerException {
        super();
    }

    public void introducirCliente(String customerEmail, String idCard, String customerName, String phone, double creditLimit, LocalDate birthDate) throws LogicLayerException, CustomerAgeException {
        if (clienteValido(birthDate)) {
            try {
                Customer cliente = new Customer(customerEmail, idCard, customerName, phone, creditLimit, birthDate);
                this.getCustomersDAO().save(cliente);

            } catch (SQLException e) {
                throw new LogicLayerException(e.getMessage());
            }
        } else {
            throw new CustomerAgeException("La edad del cliente no es valida");
        }

    }

    private boolean clienteValido(LocalDate birthDate) {
        boolean valido = false;
        AppConfigController appconfig = ((AppConfigController) Manager.getInstance().getController(AppConfigController.class));
        AppConfig config = ((AppConfigController) Manager.getInstance().getController(AppConfigController.class)).buildAppConfig();
        int edat = calcularEdat(birthDate);
        if (edat > config.getMinCustomerAge()) {
            valido = true;
        }
        return valido;
    }

    private int calcularEdat(LocalDate birthDate) {
        int edad;
        LocalDate fecha_actual = LocalDate.now();
        Period periodo = Period.between(birthDate, fecha_actual);
        edad = periodo.getYears();
        return edad;
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
