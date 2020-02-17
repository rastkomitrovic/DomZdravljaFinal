/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.AbstractDomainObject;

import domain.Usluga;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import service.UslugaService;
import storage.generic.GenericStorage;

/**
 *
 * @author Rastko
 */
public class UslugaServiceImpl implements UslugaService{
    
    private GenericStorage storage;

    public UslugaServiceImpl() {
        storage=new GenericStorage();
    }

    @Override
    public boolean add(Usluga u) throws SQLException {
        return storage.add(u);
    }

    @Override
    public boolean delete(Usluga u) throws SQLException {
        return storage.delete(u);
    }

    @Override
    public List<Usluga> getAll() throws SQLException {
        return (List<Usluga>)(List<?>)storage.getAll(new Usluga());
    }

    @Override
    public boolean edit(Usluga u) throws SQLException {
        return storage.edit(u);
    }

    @Override
    public Usluga findById(Usluga u) throws SQLException {
        return (Usluga)storage.findById(u);
    } 
}
