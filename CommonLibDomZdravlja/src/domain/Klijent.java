/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rastko
 */
public class Klijent extends AbstractDomainObject implements Serializable{
    private long klijentId;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private String email;

    public Klijent() {
    }
    
    

    public Klijent(String ime, String prezime, String brojTelefona, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
    }
    
    public Klijent(long klijentId,String ime,String prezime,String brojTelefona,String email){
        this.ime=ime;
        this.prezime=prezime;
        this.brojTelefona=brojTelefona;
        this.email=email;
        this.klijentId=klijentId;
        
    }
    

    
    
    /**
     * @return the klijentId
     */
    public long getKlijentId() {
        return klijentId;
    }

    /**
     * @param klijentId the klijentId to set
     */
    public void setKlijentId(long klijentId) {
        this.klijentId = klijentId;
    }

    /**
     * @return the ime
     */
    public String getIme() {
        return ime;
    }

    /**
     * @param ime the ime to set
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * @return the prezime
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * @param prezime the prezime to set
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * @return the brojTelefona
     */
    public String getBrojTelefona() {
        return brojTelefona;
    }

    /**
     * @param brojTelefona the brojTelefona to set
     */
    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    @Override
    public boolean equals(Object obj) {
        Klijent k=(Klijent)obj;
        if( klijentId==k.getKlijentId() &&ime.equals(k.getIme()) && prezime.equals(k.getPrezime()) && brojTelefona.equals(k.getBrojTelefona()) && email.equals(k.getEmail()))
            return true;
        return false;
    }

    @Override
    public String getTableName() {
        return "SELECT * FROM klijent";
    }

    @Override
    public PreparedStatement getInsertQuery(Connection conn) throws SQLException {
        String query= "INSERT INTO klijent(ime,prezime,brojTelefona,email) VALUES('"+ime+"','"+prezime+"','"+brojTelefona+"','"+email+"')";
        PreparedStatement ps=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        return ps;
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM klijent WHERE klijentId="+klijentId;
    }

    @Override
    public String getEditQuery() {
        return "UPDATE klijent SET ime='"+ime+"' , prezime='"+prezime+"' , brojTelefona='"+brojTelefona+"' , email='"+email+"' WHERE klijentId="+klijentId;
    }

    @Override
    public String getFindByIdQuery() {
        return "SELECT * FROM klijent WHERE klijentId="+klijentId;
    }

    @Override
    public List<AbstractDomainObject> getAll(ResultSet rs) {
        List<AbstractDomainObject> klijenti=new LinkedList<>();
        try{
            while(rs.next()){
                long klijentId=rs.getLong("klijentId");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
                String brojTelefona=rs.getString("brojTelefona");
                String email=rs.getString("email");
                klijenti.add(new Klijent(klijentId, ime, prezime, brojTelefona, email));
            }
            rs.close();
            return klijenti;
        }catch(SQLException e){
            System.out.println("Greska Klijent.getALL");
            e.printStackTrace();
        }
        return klijenti;
    }

    @Override
    public AbstractDomainObject getFindByIdObject(ResultSet rs) {
        try{
                rs.next();
                long klijentIdd=rs.getLong("klijentId");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
                String brojTelefona=rs.getString("brojTelefona");
                String email=rs.getString("email");
                Klijent k=new  Klijent(klijentIdd, ime, prezime, brojTelefona, email);
                rs.close();
                return k;
            
            
        }catch(SQLException e){
            System.out.println("Greska u Klijent.getFindByIdObject");
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public void setId(ResultSet generatedKeys) {
        try {
            generatedKeys.next();
            this.klijentId=generatedKeys.getLong(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Doktor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
