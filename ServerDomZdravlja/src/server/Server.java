/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import controler.Controller;
import domain.Doktor;
import domain.Klijent;
import domain.Pregled;
import domain.Termin;
import domain.User;
import domain.Usluga;
import domain.VrstaSpecijaliste;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import thread.ThreadClient;
import thread.ThreadServer;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Rastko
 */
public class Server {

    private ThreadServer threadServer;
    private ServerSocket serverSocket;
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        threadServer = new ThreadServer(serverSocket);
        threadServer.start();


        // communication(socket);
    }

    public void stopServer() {
        threadServer.emptyAllUsers();
        threadServer.interrupt();
        try{
            serverSocket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ThreadServer getThreadServer() {
        return threadServer;
    }

    public List<ThreadClient> getClients() {
        return threadServer.getUsers();
    }

    

}
