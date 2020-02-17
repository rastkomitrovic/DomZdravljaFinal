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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rastko
 */
public class Termin extends AbstractDomainObject implements Serializable{
    private long terminId;
    private Date vremeOd;
    private Date vremeDo;
    private Klijent klijent;
    private Doktor doktor;
    private Pregled pregled;
    private List<Usluga> usluge;
    public Termin() {
    }

    public Termin(long terminId, Date vremeOd, Date vremeDo, Klijent klijent, Doktor doktor, Pregled pregled,List<Usluga> usluge) {
        this.terminId = terminId;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
        this.klijent = klijent;
        this.doktor = doktor;
        this.pregled = pregled;
        this.usluge=usluge;
    }

    public Termin(Date vremeOd, Date vremeDo, Klijent klijent, Doktor doktor, Pregled pregled,List<Usluga> usluge) {
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
        this.klijent = klijent;
        this.doktor = doktor;
        this.pregled = pregled;
        this.usluge=usluge;
    }

    public long getTerminId() {
        return terminId;
    }

    public void setTerminId(long terminId) {
        this.terminId = terminId;
    }

    public Date getVremeOd() {
        return vremeOd;
    }

    public void setVremeOd(Date vremeOd) {
        this.vremeOd = vremeOd;
    }

    public Date getVremeDo() {
        return vremeDo;
    }

    public void setVremeDo(Date vremeDo) {
        this.vremeDo = vremeDo;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Doktor getDoktor() {
        return doktor;
    }

    public void setDoktor(Doktor doktor) {
        this.doktor = doktor;
    }

    public Pregled getPregled() {
        return pregled;
    }

    public void setPregled(Pregled pregled) {
        this.pregled = pregled;
    }

    @Override
    public String toString() {
        SimpleDateFormat sfd=new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sft=new SimpleDateFormat("hh:mm:ss");
        return pregled.getnazivPregleda()+" kod doktora "+doktor.getIme()+" "+doktor.getPrezime()+" datuma:"+sfd.format(vremeOd)+" u vreme:"+sft.format(vremeOd);
    }

    @Override
    public boolean equals(Object obj) {
        Termin t=(Termin)obj;
        return t.getTerminId()==terminId;
    }

    public List<Usluga> getUsluge() {
        return usluge;
    }

    public void setUsluge(List<Usluga> usluge) {
        this.usluge = usluge;
    }

    @Override
    public String getTableName() {
        return "SELECT * FROM termin\n" +
"INNER JOIN uslugaagregacija ON uslugaagregacija.terminId=termin.terminId \n" +
"INNER JOIN usluga ON usluga.uslugaId=uslugaagregacija.uslugaId \n" +
"INNER JOIN pregled ON pregled.pregledId=usluga.pregledId \n" +
"INNER JOIN doktor ON doktor.doktorId=termin.doktorId \n" +
"INNER JOIN klijent ON klijent.klijentId=termin.klijentId \n" +
"INNER JOIN vrstaspecijaliste ON vrstaspecijaliste.vrstaSpecijalisteId=doktor.vrstaSpecijalisteId";
    }

    @Override
    public PreparedStatement getInsertQuery(Connection conn) throws SQLException {
        Timestamp od=new java.sql.Timestamp(vremeOd.getTime());
        Timestamp vdo=new java.sql.Timestamp(vremeDo.getTime());
        String query="INSERT INTO termin (vremeOd,vremeDo,klijentId,doktorId,pregledId) values (?,?,?,?,?)";
        PreparedStatement ps=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        ps.setTimestamp(1, od);
        ps.setTimestamp(2, vdo);
        ps.setLong(3, klijent.getKlijentId());
        ps.setLong(4, doktor.getDoktorId());
        ps.setLong(5, pregled.getpregledId());
        return ps;
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM termin WHERE terminId="+terminId;
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
         try {
             LinkedList<AbstractDomainObject> termini=new LinkedList<>();
            while (rs.next()) {
                long terminId = rs.getLong(1);
                Date vremeOd = new Date(rs.getTimestamp(2).getTime());
                Date vremeDo = new Date(rs.getTimestamp(3).getTime());
                long klijentId=rs.getLong(4);
                String ime=rs.getString(25);
                String prezime=rs.getString(26);
                String brojTelefona=rs.getString(27);
                String email=rs.getString(28);
                Klijent klijent=new Klijent(klijentId, ime, prezime, brojTelefona, email);
                
                long vrstaSpecijalisteId=rs.getLong(16);
                String nazivVrste=rs.getString("nazivVrste");
                VrstaSpecijaliste vrstaSpecijaliste=new VrstaSpecijaliste(vrstaSpecijalisteId, nazivVrste);
                
                long pregledId = rs.getLong(6);
                String nazivPregleda = rs.getString(14);
                String opis = rs.getString(15);
                Pregled pregled=new Pregled(pregledId, nazivPregleda, opis, vrstaSpecijaliste);
                
                long uslugaId=rs.getLong(7);
                String nazivUsluge=rs.getString(10);
                String opisUsluge=rs.getString(11);
                Usluga u=new Usluga(uslugaId, opisUsluge, nazivUsluge, pregled);
                
                long doktorId = rs.getLong(5);
                String imeDoktor = rs.getString(18);
                String prezimeDoktor = rs.getString(19);
                String brojTelefonaDoktor = rs.getString(20);
                String emailDoktor = rs.getString(21);
                int brojOrdinacije = rs.getInt(22);
                Doktor doktor=new Doktor(doktorId, imeDoktor, prezimeDoktor, brojTelefonaDoktor, emailDoktor, brojOrdinacije, vrstaSpecijaliste);
                
                Termin t=new Termin(terminId, vremeOd, vremeDo, klijent, doktor, pregled, null);
                AbstractDomainObject ad=t;
                if(termini.contains(t))
                    ((Termin)termini.get(termini.lastIndexOf(t))).getUsluge().add(u);
                else{
                    LinkedList<Usluga> usluge=new LinkedList<>();
                    usluge.add(u);
                    termini.add(new Termin(terminId, vremeOd, vremeDo, klijent, doktor, pregled, usluge));
                }
                
 
            }
            rs.close();
            
            return termini;
        } catch (SQLException e) {
            System.out.println("Greska u DatabaseStorageTermin.getAll");
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
            this.terminId=generatedKeys.getLong(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Doktor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
