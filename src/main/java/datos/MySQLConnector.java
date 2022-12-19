/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * Clase encargada de realizar la conexión con MySQL
 */
public class MySQLConnector {
        
     /**
     * Conecta con una DB MySQL
     * @param bd
     * @param usuari
     * @param password
     * @return objecte Connection
     * @throws SQLException 
     */
    public static Connection ConnectarBD(String bd, String usuari, String password) throws SQLException
    {
        Connection ret = null;
        
        // Clase base para poder realizar cualquier acceso a la BBDD
         ret =  DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd+"?useUnicode=true&"
                            + "useJDBCCompliantTimezoneShift=true&"
                            + "useLegacyDatetimeCode=false&serverTimezone=UTC", usuari, password);
        
        return ret;
    }
}
