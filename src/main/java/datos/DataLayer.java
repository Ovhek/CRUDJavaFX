/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * Capa de datos
 */
public abstract class DataLayer {
    
    Connection con;

    public DataLayer() throws SQLException {
       //TODO: cambiar datos
       String db = "database";
       String user = "root";
       String password = "123456";
        //Intentamos conectar
        this.con = MySQLConnector.ConnectarBD(db,user,password);
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection _con) {
        con = _con;
    }
    
    /***
     * Cerramos la conexión del DAO
     * @throws java.sql.SQLException
     */
    public void close() throws SQLException
    {
        if (con != null)
        {
            System.out.println("Cerrando conexión: " + this.con.getMetaData().getURL());
            this.con.close();
        }
    }
            
}
