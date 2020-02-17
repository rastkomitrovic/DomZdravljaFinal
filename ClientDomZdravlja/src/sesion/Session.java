/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import domain.Doktor;
import domain.Klijent;
import domain.Pregled;
import domain.User;
import domain.Usluga;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import thread.ThreadListen;


/**
 *
 * @author Rastko
 */
public class Session {
     private static User currentUser;
     private static Doktor terminDoktor=null;
     private static Klijent terminKlijent=null;
     private static Pregled uslugaPregled=null;
     private static List<Usluga> uslugeLista=null;
     private static Socket socket;
     private static Session instance;
     private ThreadListen thread;
     private static int port;
     private static String url;
    public static Socket getSocket() {
        return socket;
    }

     public Session() throws IOException{

             
             socket=new Socket(url, port);
             thread=new ThreadListen(socket);
             thread.start();
         
     }
     public static Session getInstance() throws IOException{
         if(instance==null)
             instance=new Session();
         if(socket.isInputShutdown()|| socket.isOutputShutdown())
             throw new IOException();
         return instance;
     }

    public ThreadListen getThread() {
        return thread;
    }
     
    public static List<Usluga> getUslugeLista() {
        return uslugeLista;
    }

    public static void setUslugeLista(List<Usluga> uslugeLista) {
        Session.uslugeLista = uslugeLista;
    }

     
    public static Doktor getTerminDoktor() {
        return terminDoktor;
    }

    public static Pregled getUslugaPregled() {
        return uslugaPregled;
    }

    public static void setUslugaPregled(Pregled uslugaPregled) {
        Session.uslugaPregled = uslugaPregled;
    }

    public static void setTerminDoktor(Doktor terminDoktor) {
        Session.terminDoktor = terminDoktor;
    }

    public static Klijent getTerminKlijent() {
        return terminKlijent;
    }

    public static void setTerminKlijent(Klijent terminKlijent) {
        Session.terminKlijent = terminKlijent;
    }

   
     
     public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Session.currentUser = currentUser;
    }

    public static void setPort(int port) {
        Session.port = port;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Session.url = url;
    }

    public static int getPort() {
        return port;
    }

    public static void setSocket(Socket socket) {
        Session.socket = socket;
    }
    
    
    

}
