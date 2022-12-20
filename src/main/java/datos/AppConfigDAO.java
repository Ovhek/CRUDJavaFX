/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import aplicacion.modelo.AppConfig;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * DAO del AppConfig
 */
public class AppConfigDAO extends DataLayer implements DAOInterface<AppConfig>{

    public AppConfigDAO() throws SQLException{
        super();
    }
    
    /**
     * Función heredada de la interfaz. No es implementable pues no existe un listado de AppConfig.
     * @return null
     */
    @Override
    public List<AppConfig> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    /**
     * Añade un AppConfig a la base de datos.
     * @param t objecto AppConfig a guardar.
     */
    @Override
    public void save(AppConfig t) throws SQLException {
        String sqlQuery;
        String nomBD = this.getCon().getCatalog();
        
        sqlQuery = "INSERT INTO  "+nomBD+".appConfig VALUES (?,?,?,?,?,?,?,?);"; 
        
        PreparedStatement preparedStatement = this.getCon().prepareStatement(sqlQuery);
        preparedStatement.setFloat(1, t.getDefaultCreditLimit());
        preparedStatement.setInt(2, t.getDefaultQuantityInStock());
        preparedStatement.setInt(3, t.getDefaultQuantityOrdered());
        preparedStatement.setInt(4, t.getDefaultProductBanefit());
        preparedStatement.setInt(5, t.getMinShippingHours());
        preparedStatement.setInt(6, t.getMinCustomerAge());
        preparedStatement.setInt(7, t.getMaxLinesPerOrder());
        preparedStatement.setFloat(8, t.getMaxOrderAmount());
        
        preparedStatement.executeUpdate();

    }

    /**
     * Función no aplicable pues no se puede actualizar una tabla sin clave primaria. En este caso primero se borra el registro y después se añade el nuevo.
     * @param t objeto appConfig a modificar
     */
    @Override
    public void update(AppConfig t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(AppConfig t) throws SQLException {       
        String consulta = "DELETE FROM appConfig";
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);
        // Ejecución de la consulta
        sentencia.executeUpdate();
    }

    /**
     * Función heredada de la interfaz. No es implementable pues no existe un listado de AppConfig. 
     * SE DEBE UTILIZAR LA FUNCIÓN getAppConfig()
     * @return null
     */
    @Override
    public AppConfig get(AppConfig t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public AppConfig getAppConfig() throws SQLException{
        // Obtiene todos los registros de la tabla appConfig (solo hay 1)
        String consulta = "select * from appConfig";
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);
        // Ejecución de la consulta
        ResultSet result = sentencia.executeQuery(); 
        
        return constructAppConfig(result);
    }
    
    private AppConfig constructAppConfig(ResultSet result) throws SQLException{
        AppConfig appConfig = null;
        if(result.next()){
            appConfig = new AppConfig();
            appConfig.setDefaultCreditLimit(result.getFloat("defaultCreditLimit"));
            appConfig.setDefaultQuantityInStock(result.getInt("defaultQuantityInStock"));
            appConfig.setDefaultQuantityOrdered(result.getInt("defaultQuantityOrdered"));
            appConfig.setDefaultProductBanefit(result.getInt("defaultProductBenefit"));
            appConfig.setMinShippingHours(result.getInt("minShippingHours"));
            appConfig.setMinCustomerAge(result.getInt("minCustomerAge"));
            appConfig.setMaxLinesPerOrder(result.getInt("maxLinesPerOrder"));
            appConfig.setMaxOrderAmount(result.getFloat("maxOrderAmount"));
        }

        return appConfig;
    }
    
}
