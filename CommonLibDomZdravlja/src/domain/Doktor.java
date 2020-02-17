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
public class Doktor extends AbstractDomainObject implements Serializable{

    private long doktorId;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private String email;
    private int brojOrdinacije;
    private VrstaSpecijaliste vrstaSpecijaliste;

    public Doktor(long doktorId, String ime, String prezime, String brojTelefona, String email, int brojOrdinacije, VrstaSpecijaliste vrstaSpecijaliste) {
        this.doktorId = doktorId;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
        this.vrstaSpecijaliste = vrstaSpecijaliste;
        this.brojOrdinacije = brojOrdinacije;
    }

    public Doktor(String ime, String prezime, String brojTelefona, String email, int brojOrdinacije, VrstaSpecijaliste vrstaSpecijaliste) {
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
        this.vrstaSpecijaliste = vrstaSpecijaliste;
        this.brojOrdinacije = brojOrdinacije;
    }

    public Doktor() {
    }

    public long getDoktorId() {
        return doktorId;
    }

    public void setDoktorId(long doktorId) {
        this.doktorId = doktorId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VrstaSpecijaliste getVrstaSpecijaliste() {
        return vrstaSpecijaliste;
    }

    public void setVrstaSpecijaliste(VrstaSpecijaliste vrstaSpecijaliste) {
        this.vrstaSpecijaliste = vrstaSpecijaliste;
    }

    public int getBrojOrdinacije() {
        return brojOrdinacije;
    }

    public void setBrojOrdinacije(int brojOrdinacije) {
        this.brojOrdinacije = brojOrdinacije;
    }

    @Override
    public String toString() {
        return this.ime + " " + this.prezime;
    }

    @Override
    public boolean equals(Object obj) {
        return doktorId == ((Doktor) obj).getDoktorId();
    }

    @Override
    public String getTableName() {
        return "SELECT * FROM doktor INNER JOIN vrstaspecijaliste ON doktor.vrstaSpecijalisteId=vrstaspecijaliste.vrstaSpecijalisteId";
    }

    @Override
    public PreparedStatement getInsertQuery(Connection conn) throws SQLException{
        String query="INSERT INTO doktor(ime, prezime, brojTelefona, email, brojOrdinacije, vrstaSpecijalisteId) VALUES('" + ime + "','" + prezime + "','" + brojTelefona + "','" + email + "'," + brojOrdinacije + "," + vrstaSpecijaliste.getVrstaSpecijalisteId() + ")";
        PreparedStatement ps=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        return ps;
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM doktor WHERE doktorId=" + doktorId;
    }

    @Override
    public String getEditQuery() {
        return "UPDATE doktor SET ime='" + ime + "' , prezime='" + prezime + "' , brojTelefona='" + brojTelefona + "' , email='" + email + "' , brojOrdinacije=" + brojOrdinacije + " , vrstaSpecijalisteId=" + vrstaSpecijaliste.getVrstaSpecijalisteId() + " WHERE doktorId=" + doktorId;
    }

    @Override
    public String getFindByIdQuery() {
        return "SELECT * FROM doktor INNER JOIN vrstaspecijaliste ON doktor.vrstaSpecijalisteId=vrstaspecijaliste.vrstaSpecijalisteId WHERE doktorId=" + doktorId;
    }

    @Override
    public List<AbstractDomainObject> getAll(ResultSet rs) {
        try {
            List<AbstractDomainObject> doktori = new LinkedList<>();
            while (rs.next()) {
                long id = rs.getLong("doktorId");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String brojTelefona = rs.getString("brojTelefona");
                String email = rs.getString("email");
                int brojOrdinacije = rs.getInt("brojOrdinacije");
                long vrstaSpecijalisteId = rs.getLong("vrstaSpecijalisteId");
                String nazivVrste = rs.getString("nazivVrste");
                doktori.add(new Doktor(id, ime, prezime, brojTelefona, email, brojOrdinacije, new VrstaSpecijaliste(vrstaSpecijalisteId, nazivVrste)));
            }
            rs.close();
            return doktori;
        } catch (SQLException ex) {
            System.out.println("Greska u Doktor.getAll");
            ex.printStackTrace();
            Logger.getLogger(Doktor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public AbstractDomainObject getFindByIdObject(ResultSet rs) {
        try {
            rs.next();
            long doktorIdd = rs.getLong("doktorId");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String brojTelefona = rs.getString("brojTelefona");
            String email = rs.getString("email");
            int brojOrdinacije = rs.getInt("brojOrdinacije");
            long vrstaSpecijalisteId = rs.getLong("vrstaSpecijalisteId");
            String nazivVrste = rs.getString("nazivVrste");
            Doktor d = new Doktor(doktorIdd, ime, prezime, brojTelefona, email, brojOrdinacije, new VrstaSpecijaliste(vrstaSpecijalisteId, nazivVrste));
            rs.close();
            return d;
        } catch (SQLException e) {
            System.out.println("Greska u Doktor.getFindByIdObject");
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public void setId(ResultSet generatedKeys) {
        try {
            generatedKeys.next();
            this.doktorId=generatedKeys.getLong(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Doktor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
