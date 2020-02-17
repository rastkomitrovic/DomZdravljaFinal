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
public class Pregled extends AbstractDomainObject implements Serializable{

    private long pregledId;
    private String nazivPregleda;
    private String opis;
    private VrstaSpecijaliste vrstaSpecijalisteZaPregled;

    public Pregled() {
    }

    
    public Pregled(long pregledId, String nazivPregleda, String opis, VrstaSpecijaliste vrstaSpecijalisteZaPregled) {
        this.pregledId = pregledId;
        this.nazivPregleda = nazivPregleda;
        this.opis = opis;
        this.vrstaSpecijalisteZaPregled = vrstaSpecijalisteZaPregled;

    }

    public Pregled(String nazivPregleda, String opis, VrstaSpecijaliste vrstaSpecijalisteZaPregled) {
        this.nazivPregleda = nazivPregleda;
        this.opis = opis;
        this.vrstaSpecijalisteZaPregled = vrstaSpecijalisteZaPregled;
    }

    public long getpregledId() {
        return pregledId;
    }

    public void setpregledId(long pregledId) {
        this.pregledId = pregledId;
    }

    public String getnazivPregleda() {
        return nazivPregleda;
    }

    public void setnazivPregleda(String nazivPregleda) {
        this.nazivPregleda = nazivPregleda;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public VrstaSpecijaliste getVrstaSpecijalisteZaPregled() {
        return vrstaSpecijalisteZaPregled;
    }

    public void setVrstaSpecijalisteZaPregled(VrstaSpecijaliste vrstaSpecijalisteZaPregled) {
        this.vrstaSpecijalisteZaPregled = vrstaSpecijalisteZaPregled;
    }

    @Override
    public String toString() {
        return nazivPregleda;
    }

    @Override
    public boolean equals(Object obj) {
        Pregled p = (Pregled) obj;
        if (pregledId == p.getpregledId() && nazivPregleda.toLowerCase().equals(p.getnazivPregleda().toLowerCase()) && opis.toLowerCase().equals(p.getOpis().toLowerCase()) && vrstaSpecijalisteZaPregled.getVrstaSpecijalisteId() == p.getVrstaSpecijalisteZaPregled().getVrstaSpecijalisteId()) {
            return true;
        }
        return false;
    }

    @Override
    public String getTableName() {
        return "SELECT * FROM pregled INNER JOIN vrstaspecijaliste ON pregled.vrstaSpecijalisteId=vrstaspecijaliste.vrstaSpecijalisteId";
    }

    @Override
    public PreparedStatement getInsertQuery(Connection conn) throws SQLException {
        String query="INSERT INTO pregled( nazivPregleda, opis, vrstaSpecijalisteId) VALUES ('" + nazivPregleda + "','" + opis + "'," + vrstaSpecijalisteZaPregled.getVrstaSpecijalisteId() + ")";
        PreparedStatement ps=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        return ps;
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM pregled WHERE pregledId=" + pregledId;
    }

    @Override
    public String getEditQuery() {
        return "UPDATE pregled SET nazivPregleda='" + nazivPregleda + "' , opis='" + opis + "' , vrstaSpecijalisteId=" + vrstaSpecijalisteZaPregled.getVrstaSpecijalisteId() + " WHERE pregledId=" + pregledId;
    }

    @Override
    public String getFindByIdQuery() {
        return "SELECT * FROM pregled INNER JOIN vrstaspecijaliste ON pregled.vrstaSpecijalisteId=vrstaspecijaliste.vrstaSpecijalisteId WHERE pregledId=" + pregledId;
    }

    @Override
    public List<AbstractDomainObject> getAll(ResultSet rs) {
        LinkedList<AbstractDomainObject> pregledi = new LinkedList<>();
        try {
            while (rs.next()) {
                long pregledId = rs.getLong("pregledId");
                String nazivPregleda = rs.getString("nazivPregleda");
                String opis = rs.getString("opis");
                long vrstaSpecijalisteId = rs.getLong("vrstaSpecijalisteId");
                String nazivVrste = rs.getString("nazivVrste");
                pregledi.add(new Pregled(pregledId, nazivPregleda, opis, new VrstaSpecijaliste(vrstaSpecijalisteId, nazivVrste)));
            }
            rs.close();
            return pregledi;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Greska Pregled.getAll");
        }
        return pregledi;
    }

    @Override
    public AbstractDomainObject getFindByIdObject(ResultSet rs) {
        try {
            Pregled p = null;
            rs.next();
            long pregledIdd = rs.getLong("pregledId");
            String nazivPregleda = rs.getString("nazivPregleda");
            String opis = rs.getString("opis");
            long vrstaSpecijalisteId = rs.getLong("vrstaSpecijalisteId");
            String nazivVrste = rs.getString("nazivVrste");
            p = new Pregled(pregledIdd, nazivPregleda, opis, new VrstaSpecijaliste(vrstaSpecijalisteId, nazivVrste));
            rs.close();
            return p;
        } catch (SQLException e) {
            System.out.println("Greska u DatabaseStoragePregled.findById");
            e.printStackTrace();
        }
        return null;
    }

  

    @Override
    public void setId(ResultSet generatedKeys) {
        try {
            generatedKeys.next();
            this.pregledId=generatedKeys.getLong(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Doktor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
