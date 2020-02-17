/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.AbstractDomainObject;
import domain.VrstaSpecijaliste;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import service.VrstaSpecijalisteService;
import storage.generic.GenericStorage;

/**
 *
 * @author Rastko
 */
public class VrstaSpecijalisteServiceImpl implements VrstaSpecijalisteService {
    private GenericStorage storage;

    public VrstaSpecijalisteServiceImpl() {
        storage=new GenericStorage();
    }

    @Override
    public List<VrstaSpecijaliste> getAll() throws SQLException {
        return (List<VrstaSpecijaliste>)(List<?>)storage.getAll(new VrstaSpecijaliste());
    }

    @Override
    public boolean add(VrstaSpecijaliste vrsta) throws SQLException {
        return storage.add(vrsta);
    }  
}
