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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rastko
 */
public class Usluga extends AbstractDomainObject implements Serializable{

    private long uslugaId;
    private String opisUsluge;
    private String nazivUsluge;
    private Pregled pregled;

    public Usluga() {
    }

    public Usluga(long uslugaId, String opisUsluge, String nazivUsluge, Pregled pregled) {
        this.uslugaId = uslugaId;
        this.opisUsluge = opisUsluge;
        this.nazivUsluge = nazivUsluge;
        this.pregled = pregled;
    }

    public Usluga(String opisUsluge, String nazivUsluge, Pregled pregled) {
        this.opisUsluge = opisUsluge;
        this.nazivUsluge = nazivUsluge;
        this.pregled = pregled;
    }

    public long getUslugaId() {
        return uslugaId;
    }

    public void setUslugaId(long uslugaId) {
        this.uslugaId = uslugaId;
    }

    public String getOpisUsluge() {
        return opisUsluge;
    }

    public void setOpisUsluge(String opisUsluge) {
        this.opisUsluge = opisUsluge;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public Pregled getPregled() {
        return pregled;
    }

    public void setPregled(Pregled pregled) {
        this.pregled = pregled;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usluga other = (Usluga) obj;
        if (this.uslugaId != other.uslugaId) {
            return false;
        }
        if (!Objects.equals(this.nazivUsluge, other.nazivUsluge)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivUsluge;
    }

    @Override
    public String getTableName() {
        return "Select * from usluga inner join pregled on usluga.pregledId=pregled.pregledId inner join vrstaspecijaliste on vrstaspecijaliste.vrstaSpecijalisteId=pregled.vrstaSpecijalisteId";
    }

    @Override
    public PreparedStatement getInsertQuery(Connection conn) throws SQLException {
        String query="Insert into usluga(nazivUsluge,opisUsluge,pregledId) values ('" + nazivUsluge + "','" + opisUsluge + "'," + pregled.getpregledId() + ")";
        PreparedStatement ps=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        return ps;
    }

    @Override
    public String getDeleteQuery() {
        return "Delete from usluga where uslugaId=" + uslugaId;
    }

    @Override
    public String getEditQuery() {
        return "update usluga set nazivUsluge='"+nazivUsluge+"' , opisUsluge='"+opisUsluge+"' where uslugaId=" + uslugaId;
    }

    @Override
    public String getFindByIdQuery() {
        return "select * from usluga where uslugaId=" + uslugaId;
    }

    @Override
    public List<AbstractDomainObject> getAll(ResultSet rs) {
        try {
            List<AbstractDomainObject> usluge = new LinkedList<>();
            while (rs.next()) {
                long uslugaId = rs.getLong("uslugaId");
                String nazivUsluge = rs.getString("nazivUsluge");
                String opisUsluge = rs.getString("opisUsluge");

                long pregledId = rs.getLong("pregledId");
                String nazivPregleda = rs.getString("nazivPregleda");
                String opisPregleda = rs.getString("opis");

                long vrstaSpecijalisteId = rs.getLong("vrstaSpecijalisteId");
                String nazivVrste = rs.getString("nazivVrste");

                VrstaSpecijaliste vrstaSpecijaliste = new VrstaSpecijaliste(vrstaSpecijalisteId, nazivVrste);
                Pregled pregled = new Pregled(pregledId, nazivPregleda, opisPregleda, vrstaSpecijaliste);
                Usluga usluga = new Usluga(uslugaId, opisUsluge, nazivUsluge, pregled);

                usluge.add(usluga);
            }
            rs.close();
            return usluge;
        } catch (SQLException e) {
            System.out.println("Greska u Usluga.getAll");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AbstractDomainObject getFindByIdObject(ResultSet rs) {
        try {
            rs.next();
            long uslugaId = rs.getLong("uslugaId");
            String nazivUsluge = rs.getString("nazivUsluge");
            String opisUsluge = rs.getString("opisUsluge");

            long pregledId = rs.getLong("pregledId");
            String nazivPregleda = rs.getString("nazivPregleda");
            String opisPregleda = rs.getString("opisPregleda");

            long vrstaSpecijalisteId = rs.getLong("vrstaSpecijalisteId");
            String nazivVrste = rs.getString("nazivVrste");

            VrstaSpecijaliste vrstaSpecijaliste = new VrstaSpecijaliste(vrstaSpecijalisteId, nazivVrste);
            Pregled pregled = new Pregled(pregledId, nazivPregleda, opisPregleda, vrstaSpecijaliste);
            Usluga usluga = new Usluga(uslugaId, opisUsluge, nazivUsluge, pregled);
            rs.close();
            return usluga;
        } catch (SQLException e) {
            System.out.println("Greska u Usluga.getFindByIdObject");
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public void setId(ResultSet generatedKeys) {
        try {
            generatedKeys.next();
            this.uslugaId=generatedKeys.getLong(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Doktor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
