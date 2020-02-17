/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Doktor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rastko
 */
public interface DoktorService {
    public List<Doktor> getAll() throws SQLException;
    public boolean add(Doktor dok)throws SQLException;
    public Doktor findById(Doktor dok)throws SQLException;
    public boolean edit(Doktor dok)throws SQLException;
    public boolean delete(Doktor dok)throws SQLException;
}
