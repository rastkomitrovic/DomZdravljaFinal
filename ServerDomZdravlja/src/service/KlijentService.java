/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Klijent;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rastko
 */
public interface KlijentService {
    public List<Klijent> getAll()throws SQLException;
    public boolean add(Klijent k)throws SQLException;
    public boolean edit(Klijent k)throws SQLException;
    public boolean delete(Klijent k)throws SQLException;
    public Klijent findById(Klijent k)throws SQLException;
}
