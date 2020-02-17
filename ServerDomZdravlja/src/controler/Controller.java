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
import domain.UslugaAgregacija;
import domain.VrstaSpecijaliste;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import service.DoktorService;
import service.KlijentService;
import service.PregledService;
import service.TerminService;
import service.UserService;
import service.UslugaAgregacijaService;
import service.UslugaService;
import service.VrstaSpecijalisteService;
import service.impl.DoktorServiceImpl;
import service.impl.KlijentServiceImpl;
import service.impl.PregledServiceImpl;
import service.impl.TerminServiceImpl;
import service.impl.UserServiceImpl;
import service.impl.UslugaAgregacijaServiceImpl;
import service.impl.UslugaServiceImpl;
import service.impl.VrstaSpecijalisteServiceImpl;

/**
 *
 * @author Rastko
 */
public class Controller {

    private UserService userService;
    private DoktorService doktorService;
    private KlijentService klijentService;
    private PregledService pregledService;
    private TerminService terminService;
    private UslugaService uslugaService;
    private VrstaSpecijalisteService vrstaSpecijalisteService;
    private UslugaAgregacijaService uslugaAgregacijaService;

    private static Controller instance;

    public Controller() throws SQLException {
        userService = new UserServiceImpl();
        doktorService = new DoktorServiceImpl();
        klijentService = new KlijentServiceImpl();
        pregledService = new PregledServiceImpl();
        terminService = new TerminServiceImpl();
        uslugaService = new UslugaServiceImpl();
        vrstaSpecijalisteService = new VrstaSpecijalisteServiceImpl();
        uslugaAgregacijaService=new UslugaAgregacijaServiceImpl();
    }

    public static Controller getInstance() throws SQLException {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    //USER methods
    public User login(String username, String password) throws Exception {
        List<User> users = userService.getAll();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new Exception("Lose uneto korisnicko ime ili sifra");
    }

    public boolean addUser(User user) throws SQLException{
        return userService.add(user);
    }
    //GET METHODS
    public List<Doktor> getAllDoctors() throws SQLException {
        return doktorService.getAll();
    }

    public List<Pregled> getAllPregledi() throws SQLException {
        return pregledService.getAll();
    }

    public List<Klijent> getAllKlijenti() throws SQLException {
        return klijentService.getAll();
    }

    public List<User> getAllUsers() throws SQLException {
        return userService.getAll();
    }

    public List<VrstaSpecijaliste> getAllVrstaSpecijaliste() throws SQLException {
        return vrstaSpecijalisteService.getAll();
    }

    public List<Usluga> getAllUsluge() throws SQLException {
        return uslugaService.getAll();
    }

    public List<Termin> getAllTermini() throws SQLException {
        return terminService.getAll();
    }

    //PREGLED METHODS
    public boolean addPregled(Pregled pregled) throws Exception {
        if (!containsPregled(pregled)) {
            return pregledService.add(pregled);
        }
        throw new Exception("Ne mozete dodati pregled jer vec postoji u bazi");
    }

    public boolean editPregled(Pregled p) throws Exception {
        if(isPregledChangable(p))
        return pregledService.edit(p);
        throw new Exception("Ne mozete menjati pregled jer postoji zakazan termin za ovaj pregled");
    }

    public boolean deletePregled(Pregled p) throws Exception {
        if(isPregledChangable(p))
            return pregledService.delete(p);
        throw new Exception("Ne mozete obrisati pregled jer postoji zakazan termin za ovaj pregled");
    }

    public Pregled findPregled(Pregled pregled) throws SQLException{
        return pregledService.findById(pregled);
    }
    public boolean containsPregled(Pregled pregled) throws SQLException {
        for (Pregled p : getAllPregledi()) {
            if ( p.getnazivPregleda().equals(pregled.getnazivPregleda()) && p.getVrstaSpecijalisteZaPregled().equals(pregled.getVrstaSpecijalisteZaPregled())) {
                return true;
            }
        }
        return false;
    }

    public boolean isPregledChangable(Pregled pregled) throws SQLException{
        for(Termin t: getAllTermini())
            if(t.getPregled().equals(pregled))
                return false;
        return true;
    }
    //KLIJENT METHODS
    public boolean editKlijent(Klijent k) throws Exception {
        if (isKlijentChangable(k)) {
            return klijentService.edit(k);
        }
        throw new Exception("Ne mozete menjati klijenta jer ima zakazane termine ili termine");
    }

    public boolean deleteKlijent(Klijent k) throws Exception {
        if (isKlijentChangable(k)) {
            return klijentService.delete(k);
        }
        throw new Exception("Ne mozete obrisati klijenta jer ima zakazan termin ili termine");
    }

    public boolean addKlijent(Klijent k) throws Exception {
        if (containsKlijent(k)) {
            throw new Exception("Klijent vec postoji u bazi");
        }
        return klijentService.add(k);
    }

    public boolean containsKlijent(Klijent klijent) throws SQLException {
        for (Klijent k : getAllKlijenti()) {
            if (k.getBrojTelefona().equals(klijent.getBrojTelefona()) && k.getIme().equals(klijent.getIme()) && k.getPrezime().equals(klijent.getPrezime()) && k.getEmail().equals(klijent.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public boolean isKlijentChangable(Klijent k) throws SQLException {
        for (Termin t : getAllTermini()) {
            if (k.getKlijentId() == t.getKlijent().getKlijentId()) {
                return false;
            }
        }
        return true;
    }
    
    public Klijent findKlijent(Klijent klijent) throws SQLException{
        return klijentService.findById(klijent);
    }

    //TERMIN METHODS
    public boolean addTermin(Termin termin) throws Exception {
        if (!containsTermin(termin)) {
             if(terminService.add(termin)){
                 for(Usluga usluga : termin.getUsluge())
                    if(!uslugaAgregacijaService.add(new UslugaAgregacija(termin.getTerminId(), usluga.getUslugaId())))
                        return false;
                 return true;
             }
        } else {
            throw new Exception("Greska u dodavanju termina");
        }
        return false;
    }

    public boolean containsTermin(Termin termin) throws Exception {
        List<Termin> termini = getAllTermini();
        Date d = new Date();
        for (Termin t : termini) {
            if (t.getDoktor().equals(termin.getDoktor()) && dateBetwen(termin.getVremeOd(), t.getVremeOd(), t.getVremeDo())) {
                throw new Exception("Lekar je zauzet u izabranom terminu");
            } else if (t.getDoktor().equals(termin.getDoktor()) && dateBetwen(termin.getVremeDo(), t.getVremeOd(), t.getVremeDo())) {
                throw new Exception("Lekar je zauzet u izabranom terminu");
            } else if (t.getKlijent().equals(termin.getKlijent()) && dateBetwen(termin.getVremeDo(), t.getVremeOd(), t.getVremeDo())) {
                throw new Exception("Klijent je zauzet u izabranom terminu");
            } else if (t.getKlijent().equals(termin.getKlijent()) && dateBetwen(termin.getVremeOd(), t.getVremeOd(), t.getVremeDo())) {
                throw new Exception("Klijent je zauzet u izabranom terminu");
            }
        }
        return false;
    }

    public boolean dateBetwen(Date testDate, Date begin, Date end) {
        if (testDate.before(end) && testDate.after(begin)) {
            return true;
        }
        if (testDate.equals(begin) || testDate.equals(end)) {
            return true;
        }
        return false;
    }

    public boolean deleteTermin(Termin termin) throws SQLException {
        return terminService.delete(termin);
    }

    //DOKTOR METHODS
    public boolean containsDoktor(Doktor dok) throws SQLException {
        for (Doktor doktor : getAllDoctors()) {
            if (doktor.getIme().equals(dok.getIme()) && doktor.getPrezime().equals(dok.getPrezime()) && dok.getBrojTelefona().equals(doktor.getBrojTelefona()) && doktor.getBrojOrdinacije() == dok.getBrojOrdinacije() && dok.getEmail().equals(doktor.getEmail()) && doktor.getVrstaSpecijaliste().equals(dok.getVrstaSpecijaliste())) {
                return true;
            }
        }
        return false;

    }

    public boolean addDoktor(Doktor doktor) throws Exception {
        if (containsDoktor(doktor)) {
            throw new Exception("Doktor vec postoji u bazi");
        }
        return doktorService.add(doktor);
    }

    public boolean editDoktor(Doktor dok) throws Exception {
        if (isDoktorChangable(dok)) {
            return doktorService.edit(dok);
        }
        throw new Exception("Ne mozete menjati doktora jer ima zakazane termine");
    }

    public boolean deleteDoktor(Doktor dok) throws Exception {
        if (isDoktorChangable(dok)) {
            return doktorService.delete(dok);
        }
        throw new Exception("Ne mozete obrisati doktora zato sto ima zakazane termine");
    }

    public boolean isDoktorChangable(Doktor dok) throws SQLException {
        for (Termin t : getAllTermini()) {
            if (dok.getDoktorId() == t.getDoktor().getDoktorId()) {
                return false;
            }
        }
        return true;
    }

    public Doktor findDoktor(Doktor dok) throws SQLException{
        return doktorService.findById(dok);
    }
    //USLUGA METHODS
    public boolean addUsluga(Usluga u) throws Exception {
        if (containsUsluga(u)) {
            throw new Exception("Usluga vec postoji");
        }
        return uslugaService.add(u);
    }

    public boolean deleteUsluga(Usluga u) throws Exception {
        if(isUslugaChangable(u))
            return uslugaService.delete(u);
        else
            throw new Exception("Ne mozete izbrisati uslugu jer je zakazana za neki termin");
    }

    public boolean editUsluga(Usluga u) throws Exception {
        if(isUslugaChangable(u))
            return uslugaService.edit(u);
        else
            throw new Exception("Ne mozete menjati uslugu jer je zakazana za neki termin");
    }

    public Usluga findUsluga(Usluga u) throws SQLException {
        return uslugaService.findById(u);
    }

    public boolean containsUsluga(Usluga u) throws SQLException {
        for (Usluga usluga : Controller.getInstance().getAllUsluge()) {
            if (usluga.getNazivUsluge().equals(u.getNazivUsluge()) && usluga.getOpisUsluge().equals(u.getOpisUsluge()) && usluga.getPregled().equals(u.getPregled())) {
                return true;
            }
        }
        return false;

    }
    
    public boolean isUslugaChangable(Usluga u) throws SQLException{
        for(Termin termin: getAllTermini())
            for(Usluga usluga: termin.getUsluge())
                if(usluga.getUslugaId()==u.getUslugaId())
                    return false;
        return true;
    }
    //VRSTASPECIJALISTE METHODS
    
    public boolean addVrstaSpecijaliste(VrstaSpecijaliste vrsta) throws SQLException{
        return vrstaSpecijalisteService.add(vrsta);
    }
}
