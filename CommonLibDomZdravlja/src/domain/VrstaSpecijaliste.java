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
public class VrstaSpecijaliste extends AbstractDomainObject implements Serializable{
    long vrstaSpecijalisteId;
    String nazivVrste;

    public VrstaSpecijaliste(long vrstaSpecijalisteId, String nazivVrste) {
        this.vrstaSpecijalisteId = vrstaSpecijalisteId;
        this.nazivVrste = nazivVrste;
    }

    public VrstaSpecijaliste(String nazivVrste){
        this.nazivVrste=nazivVrste;
    }
    
    public VrstaSpecijaliste(){
        
    }
    public long getVrstaSpecijalisteId() {
        return vrstaSpecijalisteId;
    }

    public void setVrstaSpecijalisteId(long vrstaSpecijalisteId) {
        this.vrstaSpecijalisteId = vrstaSpecijalisteId;
    }

    public String getNazivVrste() {
        return nazivVrste;
    }

    public void setNazivVrste(String nazivVrste) {
        this.nazivVrste = nazivVrste;
    }

    @Override
    public String toString() {
        return nazivVrste;
    }

    @Override
    public boolean equals(Object obj) {
        VrstaSpecijaliste vr=(VrstaSpecijaliste)obj;
        if(this.vrstaSpecijalisteId==vr.getVrstaSpecijalisteId())
            return true;
        return false;
    }

    @Override
    public String getTableName() {
        return "SELECT * FROM vrstaspecijaliste";
    }

    @Override
    public PreparedStatement getInsertQuery(Connection conn) throws SQLException {
        String query= "INSERT INTO vrstaspecijaliste(nazivVrste) VALUES ('"+nazivVrste+"')";
        PreparedStatement ps=conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        return ps;
    }

    @Override
    public String getDeleteQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFindByIdQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AbstractDomainObject> getAll(ResultSet rs) {
        try{
            List<AbstractDomainObject> vrsteSpecijalista=new LinkedList<>();
            while(rs.next()){
                long vrstaSpecijalisteId=rs.getLong("vrstaSpecijalisteId");
                String nazivVrste=rs.getString("nazivVrste");
                vrsteSpecijalista.add(new VrstaSpecijaliste(vrstaSpecijalisteId, nazivVrste));
            }
            rs.close();
            return vrsteSpecijalista;
        }catch(SQLException e){
            System.out.println("Greska VrstaSpecijaliste.getAll");
            e.printStackTrace();
        }
        return null; 
    }

    @Override
    public AbstractDomainObject getFindByIdObject(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void setId(ResultSet generatedKeys) {
       try {
            generatedKeys.next();
            this.vrstaSpecijalisteId=generatedKeys.getLong(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Doktor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
}
