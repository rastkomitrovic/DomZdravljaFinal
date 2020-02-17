/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.UslugaAgregacija;
import java.sql.SQLException;
import service.UslugaAgregacijaService;
import storage.generic.GenericStorage;

/**
 *
 * @author Rastko
 */
public class UslugaAgregacijaServiceImpl implements UslugaAgregacijaService{

    private GenericStorage genericStorage;

    public UslugaAgregacijaServiceImpl() {
        genericStorage=new GenericStorage();
    }
    
    @Override
    public boolean add(UslugaAgregacija u) throws SQLException {
        return genericStorage.add(u);
    }  
}
