/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import form.FormLogin;
import form.FormServer;
import java.io.BufferedReader;
import java.io.FileReader;
import sesion.Session;

/**
 *
 * @author Rastko
 */
public class Start {
    
    public static void main(String[] args)  {
       try{
        BufferedReader br=new BufferedReader(new FileReader("configFile.txt"));
        int port=Integer.parseInt(br.readLine().split("=")[1]);
        String url=br.readLine().split("=")[1];
        Session.setUrl(url);
        Session.setPort(port);
        Session.getInstance();
        new FormLogin().setVisible(true);
       }
       catch(Exception e){
           new FormServer().setVisible(true);
       }

        //new FormServer().setVisible(true);
    }
}
