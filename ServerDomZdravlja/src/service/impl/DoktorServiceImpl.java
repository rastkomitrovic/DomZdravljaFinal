/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.AbstractDomainObject;
import domain.Doktor;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import service.DoktorService;
import storage.generic.GenericStorage;

/**
 *
 * @author Rastko
 */
public class DoktorServiceImpl implements DoktorService {

    private GenericStorage storage;

    public DoktorServiceImpl() {
        storage=new GenericStorage();
    }
    
    @Override
    public List<Doktor> getAll() throws SQLException {
        return (List<Doktor>)(List<?>)storage.getAll(new Doktor());
    }

    @Override
    public boolean add(Doktor dok) throws SQLException {
        return storage.add(dok);
    }

    @Override
    public Doktor findById(Doktor dok) throws SQLException{
        return (Doktor)storage.findById(dok);
    }

    @Override
    public boolean edit(Doktor dok)throws SQLException {
        return storage.edit(dok);
    }

    @Override
    public boolean delete(Doktor dok) throws SQLException{
        return storage.delete(dok);
    }
    
}
