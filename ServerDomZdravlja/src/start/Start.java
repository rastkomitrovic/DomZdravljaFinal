/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import form.FormServer;
import form.FrameContorl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;

/**
 *
 * @author Rastko
 */
public class Start {
    public static void main(String[] args) {
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("configFile.txt"));
            int port=Integer.parseInt(br.readLine().split("=")[1]);
            Server server=new Server();
            server.start(port);
            session.Session.setServer(server);
            session.Session.setPort(port);
            new FrameContorl().setVisible(true);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            new FormServer().setVisible(true);
            //Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
