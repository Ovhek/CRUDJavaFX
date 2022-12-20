/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import aplicacion.modelo.AppConfig;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de manejar la capa logica del AppConfig
 * @author Cole
 */
public class AppConfigLogic extends LogicLayer{

    public AppConfigLogic() throws LogicLayerException {
        super();
    }

    /**
     * Obtiene los datos del appConfig de la base de datos como un objeto de tipo AppConfig.
     * @return Objeto AppConfig o null en caso de no existir datos.
     */
    public AppConfig getAppConfig() throws LogicLayerException{
        AppConfig appConfig;
        try {
            appConfig = this.getAppConfigDAO().getAppConfig();
        }
        catch (SQLException ex) {
            throw new LogicLayerException("Error capa logica: " + ex.getMessage());
        }

        return appConfig;
    }
    
    /**
     * Guarda en la base de datos el objeto AppConfig pasado por la vista.
     */
    public void save(AppConfig appConfig) throws LogicLayerException{
        try {
            this.getAppConfigDAO().save(appConfig);
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa logica: " + ex.getMessage());
        }
    }
    
     /**
     * Actualiza en la base de datos el objeto AppConfig pasado por la vista.
     */
    public void update(AppConfig appConfig) throws LogicLayerException{
        try {
            this.getAppConfigDAO().update(appConfig);
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa logica: " + ex.getMessage());
        }
    }
    
     /**
     * Elimina todos los datos de AppConfig
     */
    public void delete(AppConfig appConfig) throws LogicLayerException{
        try {
            this.getAppConfigDAO().delete(appConfig);
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa logica: " + ex.getMessage());
        }
    }
    
    // Cerramos la conexión
    @Override
    public void close() throws LogicLayerException {
        try {
            this.getAppConfigDAO().close();
        } catch (SQLException ex) {
            throw new LogicLayerException("Error capa logica: " + ex.getMessage());
        }
    }
    
}
