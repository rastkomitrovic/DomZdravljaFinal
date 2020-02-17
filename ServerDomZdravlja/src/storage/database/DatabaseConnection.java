/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rastko
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private static Connection connection;
    
    public DatabaseConnection()throws SQLException{
        BufferedReader br=null;
        try {
            br = new BufferedReader(new FileReader("DBConfig.txt"));
            String url=br.readLine().split("=")[1];
            String dbUser=br.readLine().split("=")[1];
            String dbPass;
            try{
                dbPass=br.readLine().split("=")[1];
            }catch(ArrayIndexOutOfBoundsException e){
                dbPass="";
            }
            connection=DriverManager.getConnection(url, dbUser, dbPass);
            connection.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static DatabaseConnection getInstance() throws SQLException{
        if(instance==null)
            instance=new DatabaseConnection();
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
}
