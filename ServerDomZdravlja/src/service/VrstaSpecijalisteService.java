/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.VrstaSpecijaliste;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rastko
 */
public interface VrstaSpecijalisteService {
    public List<VrstaSpecijaliste> getAll()throws SQLException;
    public boolean add(VrstaSpecijaliste vrsta)throws SQLException;
    
}
