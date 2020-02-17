    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Termin;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rastko
 */
public interface TerminService {
    public List<Termin> getAll()throws SQLException;
    public boolean add(Termin termin)throws SQLException;
    public boolean delete(Termin termin)throws SQLException;
    
}
