/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import aplicacion.modelo.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de Customers
 */
public class CustomersDAO extends DataLayer implements DAOInterface<Customer> {

    public CustomersDAO() throws SQLException {
    }

    @Override
    public List<Customer> getAll() throws SQLException {

        Connection conecxion = MySQLConnector.ConnectarBD("m03uf6_22_23", "admin@localhost", "123456");

        Statement sentencia = conecxion.createStatement();
        ResultSet result = sentencia.executeQuery("SELECT * FROM customers");

        List<Customer> lista = new ArrayList<>();

        while (result.next()) {
            String customerEmail = result.getNString("customerEmail");
            String idCard = result.getNString("idCard");
            String customerName = result.getNString("customerName");
            String phone = result.getNString("phone");
            double creditLimit = result.getDouble("creditLimit");
            String birthDate = result.getDate("birthDate").toString();

            Customer cliente = new Customer(customerEmail, idCard, customerName, phone, creditLimit, birthDate);
            lista.add(cliente);
        }
        conecxion.close();
        return lista;
    }

    @Override
    public void save(Customer t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Customer t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Customer t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Customer get(Customer t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
