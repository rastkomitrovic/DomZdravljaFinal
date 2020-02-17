/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

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
import java.util.List;
import sesion.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;


/**
 *
 * @author Rastko
 */
public class Controller {

    

    private static Controller instance;
   
    public Controller() {
        
    }

    public static Controller getInstance() throws IOException {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }


    //USER methods
    public synchronized User login(String username, String password) throws Exception {
        Request req=new Request(Operation.OPERATION_LOGIN, new User(0,null,null,username,password,null));
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return (User)res.getData();
    }


    //GET METHODS
    public synchronized List<Doktor> getAllDoctors() throws Exception{
        Request req=new Request(Operation.OPERATION_GET_ALL_DOKTORI, null);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return (List<Doktor>)res.getData();
    }

    public synchronized List<Pregled> getAllPregledi() throws Exception {
        Request req=new Request(Operation.OPERATION_GET_ALL_PREGLEDI, null);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return (List<Pregled>)res.getData();
    }

    public synchronized List<Klijent> getAllKlijenti() throws Exception {
       Request req=new Request(Operation.OPERATION_GET_ALL_KLIJENTI, null);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return (List<Klijent>)res.getData();
    }

    public synchronized List<User> getAllUsers() throws Exception {
        Request req=new Request(Operation.OPERATION_GET_ALL_USERS, null);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);
 
        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return (List<User>)res.getData();
    }

    public synchronized List<VrstaSpecijaliste> getAllVrstaSpecijaliste() throws Exception{
        Request req=new Request(Operation.OPERATION_GET_ALL_VRSTE_SPECIJALISTE, null);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return (List<VrstaSpecijaliste>)res.getData();
    }
    
    public synchronized List<Usluga> getAllUsluge() throws Exception{
        Request req=new Request(Operation.OPERATION_GET_ALL_USLUGE, null);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return (List<Usluga>)res.getData();
    }

    //PREGLED METHODS
    public synchronized boolean addPregled(String nazivPregleda, String opis, VrstaSpecijaliste vrsta) throws Exception{
        Pregled p = new Pregled(nazivPregleda, opis, vrsta);
        Request req=new Request(Operation.OPERATION_ADD_PREGLED, p);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }

    public synchronized boolean updatePregled(Pregled p) throws Exception {
        Request req=new Request(Operation.OPERATION_EDIT_PREGLED, p);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }

    public synchronized boolean obrisiPregled(Pregled p) throws Exception {
        Request req=new Request(Operation.OPERATION_DELETE_PREGLED, p);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }


    //KLIJENT METHODS
    public synchronized boolean updateKlijent(Klijent k) throws Exception {
        Request req=new Request(Operation.OPERATION_EDIT_KLIJENT, k);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }

    public synchronized boolean deleteKlijent(Klijent k) throws Exception {
        Request req=new Request(Operation.OPERATION_DELETE_KLIJENT, k);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }

    public synchronized boolean addKlijent(Klijent k) throws Exception{
        Request req=new Request(Operation.OPERATION_ADD_KLIJENT, k);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }

    

    //TERMIN METHODS
    public synchronized List<Termin> getAllTermini() throws Exception{
        Request req=new Request(Operation.OPERATION_GET_ALL_TERMINI, null);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return (List<Termin>)res.getData();
    }

    public synchronized boolean addTermin(Termin termin) throws Exception{
        Request req=new Request(Operation.OPERATION_ADD_TERMIN, termin);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }



    public synchronized boolean deleteTermin(Termin termin) throws Exception {
        Request req=new Request(Operation.OPERATION_DELETE_TERMIN, termin);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }

    
    //DOKTOR METHODS

    public synchronized boolean addDoktor(Doktor doktor) throws Exception{
        Request req=new Request(Operation.OPERATION_ADD_DOKTOR, doktor);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }
    
    public synchronized boolean updateDoktor(Doktor dok) throws Exception {
        Request req=new Request(Operation.OPERATION_EDIT_DOKTOR, dok);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }
    
    public synchronized boolean deleteDoktor(long id) throws Exception{
        Request req=new Request(Operation.OPERATION_DELETE_DOKTOR, new Doktor(id, null, null, null, null, 0, null));
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }


    
    //USLUGA METHODS
    public synchronized boolean addUsluga(Usluga u) throws Exception{
        Request req=new Request(Operation.OPERATION_ADD_USLUGA, u);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }
    
    public synchronized boolean deleteUsluga(Usluga u) throws Exception{
        Request req=new Request(Operation.OPERATION_DELETE_USLUGA, u);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }
    
    public synchronized boolean editUsluga(Usluga u) throws Exception{
        Request req=new Request(Operation.OPERATION_EDIT_USLUGA, u);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return true;
    }
    
    public synchronized Usluga findUsluga(Usluga u) throws Exception{
        Request req=new Request(Operation.OPERATION_FIND_BY_ID_USLUGA, u);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();
        else
            return (Usluga)res.getData();
    }

    public synchronized void ping()throws Exception{
        Request req=new Request(Operation.OPERATION_PING, null);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

        ObjectInputStream in=new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res=(Response)in.readObject();
        if(res.getStatus().equals(ResponseStatus.ERROR))
            throw res.getError();

    }

    public synchronized void logOut() throws Exception {
        Request req=new Request(Operation.OPERATION_LOGOUT, null);
        ObjectOutputStream out=new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);

    }
}
