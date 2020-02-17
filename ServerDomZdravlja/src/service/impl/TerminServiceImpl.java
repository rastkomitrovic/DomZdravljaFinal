/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.AbstractDomainObject;
import domain.Termin;
import domain.Usluga;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import service.TerminService;
import storage.generic.GenericStorage;

/**
 *
 * @author Rastko
 */
public class TerminServiceImpl implements TerminService {
    
    private GenericStorage storage;

    public TerminServiceImpl() {
        storage=new GenericStorage();
    }

    @Override
    public List<Termin> getAll() throws SQLException {
        return (List<Termin>)(List<?>)storage.getAll(new Termin());
    }

    @Override
    public boolean add(Termin termin) throws SQLException {
       return storage.add(termin);
    }

    @Override
    public boolean delete(Termin termin) throws SQLException {
        return storage.delete(termin);
    }
}
