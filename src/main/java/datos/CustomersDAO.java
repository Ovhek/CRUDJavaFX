/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import aplicacion.modelo.Customer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
* Clase de acceso a datos de clientes.
*/
public class CustomersDAO extends DataLayer implements DAOInterface<Customer> {

    public CustomersDAO() throws SQLException {
    }

    /**
     * Devuelve todos los clientes de la base de datos.
     * @return Lista de todos los clientes.
     */
    @Override
    public List<Customer> getAll() throws SQLException {
        this.createConection();
        Connection conecxion = this.getCon();

        Statement sentencia = conecxion.createStatement();
        ResultSet result = sentencia.executeQuery("SELECT * FROM customers");

        List<Customer> lista = new ArrayList<>();

        while (result.next()) {
            String customerEmail = result.getString("customerEmail");
            String idCard = result.getString("idCard");
            String customerName = result.getString("customerName");
            String phone = result.getString("phone");
            double creditLimit = result.getDouble("creditLimit");
            LocalDate birthDate = result.getDate("birthDate").toLocalDate();

            Customer cliente = new Customer(customerEmail, idCard, customerName, phone, creditLimit, birthDate);
            lista.add(cliente);
        }
        conecxion.close();
        return lista;
    }

    /**
     * Guarda un cliente en la base de datos.
     * @param t objeto cliente a guardar.
     */
    @Override
    public void save(Customer t) throws SQLException {
        this.createConection();
        Connection conecxion = this.getCon();
        Statement sentencia = conecxion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        sentencia.executeQuery("SELECT * FROM customers");
        ResultSet result = sentencia.getResultSet();
        result.moveToInsertRow();
        result.updateString("customerEmail", t.getCustomerEmail());
        result.updateString("idCard", t.getIdCard());
        result.updateString("customerName", t.getCustomerName());
        result.updateString("phone", t.getPhone());
        result.updateDouble("creditLimit", t.getCreditLimit());
        result.updateDate("birthDate", Date.valueOf(t.getBirthDate()));
        result.insertRow();
        conecxion.close();
    }

    /**
     * Actualiza un cliente de la base de datos.
     * @param t Objeto cliente a actualizar.
     */
    @Override
    public void update(Customer t) throws SQLException {
        this.createConection();
        Connection conecxion = this.getCon();
        Statement sentencia = conecxion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        sentencia.executeQuery("SELECT * FROM customers WHERE customerEmail = '" + t.getCustomerEmail() + "'");
        ResultSet result = sentencia.getResultSet();
        if (result.next()) {
            result.updateString("customerEmail", t.getCustomerEmail());
            result.updateString("idCard", t.getIdCard());
            result.updateString("customerName", t.getCustomerName());
            result.updateString("phone", t.getPhone());
            result.updateDouble("creditLimit", t.getCreditLimit());
            result.updateDate("birthDate", Date.valueOf(t.getBirthDate()));
            result.updateRow();
        }
        conecxion.close();
    }

    /**
     * Elimina un cliente de la base de datos.
     * @param t Objeto representativo del cliente.
     */
    @Override
    public void delete(Customer t) throws SQLException {
        this.createConection();
        Connection conecxion = this.getCon();
        Statement sentencia = conecxion.createStatement();
        String str_sql = "DELETE FROM customers WHERE customerEmail = '" + t.getCustomerEmail() + "'";
        sentencia.executeUpdate(str_sql);
        conecxion.close();
    }

    /**
     * Función no implementada.
     */
    @Override
    public Customer get(Customer t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
