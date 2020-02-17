/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

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
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Rastko
 */
public class ThreadClient extends Thread {

    private Socket socket;
    private ThreadServer threadServer;
    private User user;
    
    public ThreadClient(Socket socket,ThreadServer threadServer) {
        this.socket = socket;
        this.threadServer=threadServer;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {

                while (true) {
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    Object obj = in.readObject();
                    Request req = (Request) obj;
                    Response res = handleRequest(req);
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    if(res!=null)
                        out.writeObject(res);
                }
            } catch (Exception ex) {
                //System.out.println("Greska u ThreadClient.communication");
                //ex.printStackTrace();
                interrupt();
                //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        threadServer.cancelCommunication(this);
        
    }

    private Response handleRequest(Request req) {
        int operation = req.getOperation();
        if(operation==Operation.OPERATION_PING){
            return new Response(ResponseStatus.SUCCESS, null, null);
        }
        if (operation == 1) {
            User u = (User) req.getData();
            Response response = new Response();
            try {
                User respUser = Controller.getInstance().login(u.getUsername(), u.getPassword());
                response.setData(respUser);
                response.setError(null);
                response.setStatus(ResponseStatus.SUCCESS);
                this.user=respUser;
                threadServer.getTableModelUser().refresh();
                return response;
            } catch (Exception ex) {
                response.setData(null);
                response.setError(ex);
                response.setStatus(ResponseStatus.ERROR);
                return response;
            }
        }
        if(operation ==Operation.OPERATION_LOGOUT){
            threadServer.cancelCommunication(this);
            return null;
        }
        if (operation >= 2 && operation <= 8) {
            return handleGetAll(req);
        }
        if (operation >= 9 && operation <= 15) {
            return handleAdd(req);
        }
        if (operation >= 16 && operation <= 20) {
            return handleDelete(req);
        }
        if (operation >= 21 && operation <= 24) {
            return handleEdit(req);
        }
        if (operation >= 25 && operation <= 28) {
            return handleFindById(req);
        }
        return null;
    }

    private Response handleGetAll(Request req) {
        Response res = new Response();
        try {
            switch (req.getOperation()) {
                case 2:
                    res.setData(Controller.getInstance().getAllKlijenti());
                    res.setError(null);
                    res.setStatus(ResponseStatus.SUCCESS);
                    break;
                case 3:
                    res.setData(Controller.getInstance().getAllDoctors());
                    res.setError(null);
                    res.setStatus(ResponseStatus.SUCCESS);
                    break;
                case 4:
                    res.setData(Controller.getInstance().getAllPregledi());
                    res.setError(null);
                    res.setStatus(ResponseStatus.SUCCESS);
                    break;
                case 5:
                    res.setData(Controller.getInstance().getAllUsluge());
                    res.setError(null);
                    res.setStatus(ResponseStatus.SUCCESS);
                    break;
                case 6:
                    res.setData(Controller.getInstance().getAllTermini());
                    res.setError(null);
                    res.setStatus(ResponseStatus.SUCCESS);
                    break;
                case 7:
                    res.setData(Controller.getInstance().getAllVrstaSpecijaliste());
                    res.setError(null);
                    res.setStatus(ResponseStatus.SUCCESS);
                    break;
                case 8:
                    res.setData(Controller.getInstance().getAllUsers());
                    res.setError(null);
                    res.setStatus(ResponseStatus.SUCCESS);
                    break;
            }
            return res;
        } catch (Exception e) {
            res.setData(null);
            res.setError(e);
            res.setStatus(ResponseStatus.ERROR);
            return res;
        }
    }

    private Response handleAdd(Request req) {
        Response res = new Response();
        try {
            switch (req.getOperation()) {
                case Operation.OPERATION_ADD_KLIJENT:
                    if (Controller.getInstance().addKlijent((Klijent) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Klijent nije dodat"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
                case Operation.OPERATION_ADD_DOKTOR:
                    if (Controller.getInstance().addDoktor((Doktor) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Doktor nije dodat"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
                case Operation.OPERATION_ADD_PREGLED:
                    if (Controller.getInstance().addPregled((Pregled) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Pregled nije dodat"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
                case Operation.OPERATION_ADD_USLUGA:
                    if (Controller.getInstance().addUsluga((Usluga) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Usluga nije dodata"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
                case Operation.OPERATION_ADD_TERMIN:
                    if (Controller.getInstance().addTermin((Termin) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Termin nije dodat"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
                case Operation.OPERATION_ADD_VRSTA_SPECIJALISTE:
                    if (Controller.getInstance().addVrstaSpecijaliste((VrstaSpecijaliste) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Vrsta specijaliste nije dodata"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
                case Operation.OPERATION_ADD_USER:
                    if (Controller.getInstance().addUser((User) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Korisnik nije dodat"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
            }
            return res;
        } catch (Exception e) {
            res.setData(null);
            res.setError(e);
            res.setStatus(ResponseStatus.ERROR);
            return res;
        }
    }

    private Response handleDelete(Request req) {
        Response res = new Response();
        try {
            switch (req.getOperation()) {
                case 16:
                    if (Controller.getInstance().deleteKlijent((Klijent) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Klijent nije izbrisan"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
                case 17:
                    if (Controller.getInstance().deleteDoktor((Doktor) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Doktor nije izbrisan"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
                case 18:
                    if (Controller.getInstance().deletePregled((Pregled) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Pregled nije izbrisan"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
                case 19:
                    if (Controller.getInstance().deleteUsluga((Usluga) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Usluga nije izbrisana"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
                case 20:
                    if (Controller.getInstance().deleteTermin((Termin) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Termin nije izbrisan"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
            }
            return res;
        } catch (Exception e) {
            res.setData(null);
            res.setError(e);
            res.setStatus(ResponseStatus.ERROR);
            return res;
        }
    }

    private Response handleEdit(Request req) {
        Response res = new Response();
        try {
            switch (req.getOperation()) {
                case 21:
                    if (Controller.getInstance().editKlijent((Klijent) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Klijent nije izbrisan"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
                case 22:
                    if (Controller.getInstance().editDoktor((Doktor) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Doktor nije izbrisan"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
                case 23:
                    if (Controller.getInstance().editPregled((Pregled) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Pregled nije izbrisan"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
                case 24:
                    if (Controller.getInstance().editUsluga((Usluga) req.getData())) {
                        res.setData(null);
                        res.setError(null);
                        res.setStatus(ResponseStatus.SUCCESS);;
                    } else {
                        res.setData(null);
                        res.setError(new Exception("Usluga nije izbrisana"));
                        res.setStatus(ResponseStatus.ERROR);
                    }
                    break;
            }
            return res;
        } catch (Exception e) {
            res.setData(null);
            res.setError(e);
            res.setStatus(ResponseStatus.ERROR);
            return res;
        }
    }

    private Response handleFindById(Request req) {
        Response res = new Response();
        try {
            switch (req.getOperation()) {
                case 25:
                    res.setData(Controller.getInstance().findKlijent((Klijent) req.getData()));
                    res.setError(null);
                    res.setStatus(ResponseStatus.SUCCESS);
                    break;
                case 26:
                    res.setData(Controller.getInstance().findDoktor((Doktor) req.getData()));
                    res.setError(null);
                    res.setStatus(ResponseStatus.SUCCESS);
                    break;
                case 27:
                    res.setData(Controller.getInstance().findPregled((Pregled) req.getData()));
                    res.setError(null);
                    res.setStatus(ResponseStatus.SUCCESS);
                    break;
                case 28:
                    res.setData(Controller.getInstance().findUsluga((Usluga) req.getData()));
                    res.setError(null);
                    res.setStatus(ResponseStatus.SUCCESS);
                    break;

            }
            return res;
        } catch (Exception e) {
            res.setData(null);
            res.setError(e);
            res.setStatus(ResponseStatus.ERROR);
            return res;
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public ThreadServer getThreadServer() {
        return threadServer;
    }

    public String getIme() {
        return user.getFirstName();
    }

    public String getPrezime() {
        return user.getLastName();
    }

    public long getUserId() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }

    
    void abort() {
        ObjectOutputStream out=null;
        try {
            Response res=new Response(ResponseStatus.SUCCESS, null, Operation.OPERATION_LOGOUT);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(res);
            this.interrupt();
            threadServer.cancelCommunication(this);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ThreadClient)
            return (((ThreadClient)obj).getUser().getId()==user.getId());
        return false;
    }

    
    
}
