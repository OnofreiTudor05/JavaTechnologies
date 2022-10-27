package databasemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tudor Onofrei
 */
public class DatabaseConnector {
    static boolean isCreated = false;
    static Connection databaseConnection = null;
    
    static Connection getConnectionManager(){
        isCreated = true;
        Properties connectionProps = new Properties();
        
        String url = "jdbc:postgresql://localhost:5432/SportTournament";
        String userDb = "postgres";
        String password = "shankfalcone1999";
    
        connectionProps.put("user", userDb);
        connectionProps.put("password", password);
        
        try { 
            databaseConnection = DriverManager.getConnection(url, connectionProps);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return databaseConnection;
    }
    
    public static Connection getConnectionInstance(){
        if(!isCreated){
            databaseConnection = getConnectionManager();
        }
        return databaseConnection;
    }
    
}
