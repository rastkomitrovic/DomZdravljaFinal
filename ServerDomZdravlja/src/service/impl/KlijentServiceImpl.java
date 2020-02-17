/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.AbstractDomainObject;
import domain.Klijent;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import service.KlijentService;
import storage.generic.GenericStorage;

/**
 *
 * @author Rastko
 */
public class KlijentServiceImpl implements KlijentService{

    private GenericStorage storage;

    public KlijentServiceImpl() {
        storage=new GenericStorage();
    }
    
    @Override
    public List<Klijent> getAll() throws SQLException {
        return (List<Klijent>)(List<?>)storage.getAll(new Klijent());
    }

    @Override
    public boolean add(Klijent k) throws SQLException {
       return storage.add(k);
    }

    @Override
    public boolean edit(Klijent k) throws SQLException {
        return storage.edit(k);
    }

    @Override
    public boolean delete(Klijent k) throws SQLException {
        return storage.delete(k);
    }
    

    @Override
    public Klijent findById(Klijent k) throws SQLException {
        return (Klijent)storage.findById(k);
    }
    
    
}
