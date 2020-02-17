/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.net.Socket;
import java.net.SocketException;
import sesion.Session;

/**
 *
 * @author Rastko
 */
public class ThreadListen extends Thread{
    private MojInterfejs mojInterfejs;
    private Socket socket;

    public ThreadListen() {
    }

    public ThreadListen(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            while(true){
                Thread.sleep(500);
                if(Session.getSocket()!=null)
                    controler.Controller.getInstance().ping();
            }
        }catch(Exception e){
            mojInterfejs.endProgram();
        }
    }

    public void setMojInterfejs(MojInterfejs mojInterfejs) {
        this.mojInterfejs = mojInterfejs;
    }
    
    
    
}
