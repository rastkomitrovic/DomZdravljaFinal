/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.AbstractDomainObject;
import domain.Pregled;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import service.PregledService;
import storage.generic.GenericStorage;

/**
 *
 * @author Rastko
 */
public class PregledServiceImpl implements PregledService {
   
    private GenericStorage storage;

    public PregledServiceImpl() {
        storage=new GenericStorage();
    }

    @Override
    public List<Pregled> getAll() throws SQLException {
        return (List<Pregled>)(List<?>)storage.getAll(new Pregled());
    }

    @Override
    public boolean add(Pregled pregled) throws SQLException {
        return storage.add(pregled);
    }

    @Override
    public boolean edit(Pregled pregled) throws SQLException {
        return storage.edit(pregled);
    }

    @Override
    public boolean delete(Pregled pregled) throws SQLException {
        return storage.delete(pregled);
    }

    @Override
    public Pregled findById(Pregled pregled) throws SQLException {
        return (Pregled)storage.findById(pregled);
    } 
}
