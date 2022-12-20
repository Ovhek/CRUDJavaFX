/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import aplicacion.modelo.AppConfig;
import aplicacion.modelo.Customer;
import datos.CustomersDAO;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.AppConfigController;

/**
 * Clase que representa la lógica de negocio relacionada con los clientes.
 * 
 */
public class CustomersLogic extends LogicLayer {

    /**
     * Constructor de la clase.
     *
     * @throws LogicLayerException Si hay un error en la capa de lógica.
     */
    public CustomersLogic() throws LogicLayerException {
        super();
    }

    /**
     * Método que permite introducir un nuevo cliente en la base de datos.
     *
     * @param customer Cliente a introducir.
     * @throws LogicLayerException Si hay un error en la capa de lógica.
     * @throws CustomerAgeException Si la edad del cliente no es válida.
     */
    public void introducirCliente(Customer customer) throws LogicLayerException, CustomerAgeException {
        if (clienteValido(customer.getBirthDate())) {
            try {
                Customer cliente = new Customer(customer.getCustomerEmail(), customer.getIdCard(), customer.getCustomerName(), customer.getPhone(), customer.getCreditLimit(), customer.getBirthDate());
                this.getCustomersDAO().save(cliente);

            } catch (SQLIntegrityConstraintViolationException e) {
                throw new LogicLayerException("El Email introducido ya esta registrado");
            } catch (SQLException e) {
                throw new LogicLayerException(e.getMessage());
            }
        } else {
            throw new CustomerAgeException("La edad del cliente no es valida");
        }

    }

    /**
     * Método que permite modificar un cliente existente en la base de datos.
     *
     * @param customer Cliente a modificar.
     * @throws LogicLayerException Si hay un error en la capa de lógica.
     * @throws CustomerAgeException Si la edad del cliente no es válida.
     */
    public void modificarCliente(Customer customer) throws LogicLayerException, CustomerAgeException {
        if (clienteValido(customer.getBirthDate())) {
            try {
                Customer cliente = new Customer(customer.getCustomerEmail(), customer.getIdCard(), customer.getCustomerName(), customer.getPhone(), customer.getCreditLimit(), customer.getBirthDate());
                this.getCustomersDAO().update(cliente);

            } catch (SQLException e) {
                throw new LogicLayerException(e.getMessage());
            }
        } else {
            AppConfig config = ((AppConfigController) Manager.getInstance().getController(AppConfigController.class)).buildAppConfig();
            int edadMin = config.getMinCustomerAge();
            throw new CustomerAgeException("La edad minima del cliente debe ser " + edadMin);
        }
    }
    /**
     * Método que permite eliminar un cliente de la base de datos.
     *
     * @param cliente Cliente a eliminar.
     * @throws LogicLayerException Si hay un error en la capa de lógica.
     */
    public void eliminarCliente(Customer cliente) throws LogicLayerException {
        try {
            this.getCustomersDAO().delete(cliente);
        } catch (SQLException e) {
            throw new LogicLayerException(e.getMessage());
        }
    }

    /**
     * Método que verifica si un cliente es válido según su edad.
     *
     * @param birthDate Fecha de nacimiento del cliente.
     * @return {@code true} si el cliente es válido, {@code false} en caso contrario.
     */
    private boolean clienteValido(LocalDate birthDate) {
        boolean valido = false;
        AppConfig config = ((AppConfigController) Manager.getInstance().getController(AppConfigController.class)).buildAppConfig();
        int edat = calcularEdat(birthDate);
        if (edat > config.getMinCustomerAge()) {
            valido = true;
        }
        return valido;
    }

    /**
     * Método que calcula la edad de un cliente a partir de su fecha de nacimiento.
     *
     * @param birthDate Fecha de nacimiento del cliente.
     * @return Edad del cliente.
     */
    private int calcularEdat(LocalDate birthDate) {
        int edad;
        LocalDate fecha_actual = LocalDate.now();
        Period periodo = Period.between(birthDate, fecha_actual);
        edad = periodo.getYears();
        return edad;
    }

    /**
     * Método que obtiene todos los clientes de la base de datos.
     *
     * @return Lista de clientes.
     * @throws LogicLayerException Si hay un error en la capa de lógica.
     */
    public List<Customer> obtenerDatos() throws LogicLayerException {
        ArrayList<Customer> lista = new ArrayList<>();
        try {
            lista.addAll(this.getCustomersDAO().getAll());
        } catch (SQLException e) {
            throw new LogicLayerException(e.getMessage());
        }
        return lista;
    }

    /**
     * Método que cierra la conexión con la base de datos.
     *
     * @throws LogicLayerException Si hay un error en la capa de lógica.
     */
    @Override
    public void close() throws LogicLayerException {
        try {
            this.getAppConfigDAO().close();
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa logica: " + ex.getMessage());
        }
    }

}
