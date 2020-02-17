/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Pregled;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rastko
 */
public interface PregledService {
    public List<Pregled> getAll()throws SQLException;
    public boolean add(Pregled pregled)throws SQLException;
    public boolean edit(Pregled pregled)throws SQLException;
    public boolean delete(Pregled pregled)throws SQLException;
    public Pregled findById(Pregled pregled)throws SQLException;        
}
