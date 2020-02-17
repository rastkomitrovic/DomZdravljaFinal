/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Usluga;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rastko
 */
public interface UslugaService {
    public boolean add(Usluga u)throws SQLException;
    public boolean delete(Usluga u)throws SQLException;
    public List<Usluga> getAll()throws SQLException;
    public boolean edit(Usluga u)throws SQLException;
    public Usluga findById(Usluga u)throws SQLException;
}
