/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import domain.User;
import form.TableModelUser;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Rastko
 */
public class ThreadServer extends Thread{
    private List<ThreadClient>users;
    private ServerSocket serverSocket;
    private TableModelUser tableModelUser;
    public ThreadServer(ServerSocket serverSocket){
        users=new LinkedList<>();
        this.serverSocket=serverSocket;
        tableModelUser=new TableModelUser(users);
    }

    @Override
    public void run() {
        while(!isInterrupted()){
            try {
                Socket socket=serverSocket.accept();
                ThreadClient thc=new ThreadClient(socket, this);
                users.add(thc);
                thc.start();
            } catch (IOException ex) {
                //ex.printStackTrace();
            }
        }
        try{
            serverSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    } 
    
    public void cancelCommunication(ThreadClient threadClient){
        try {
            users.remove(threadClient);
            threadClient.interrupt();
            threadClient.getSocket().close();
            tableModelUser.refresh();
        } catch (IOException ex) {
            ex.printStackTrace();
            users.remove(threadClient);
            tableModelUser.refresh();
        }
        
    }

    public List<ThreadClient> getUsers() {
        return users;
    }

    public TableModelUser getTableModelUser() {
        return tableModelUser;
    }

    public void emptyAllUsers() {
        try{
        if(users.size()>=1)
            for(ThreadClient thc: users){
                thc.interrupt();
                thc.getSocket().close();
            }
        users.removeAll(users);
        tableModelUser.refresh();
        }catch(IOException e){
            e.printStackTrace();
        }

    }



    
}
