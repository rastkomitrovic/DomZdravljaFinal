/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import domain.AbstractDomainObject;
import domain.User;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import service.UserService;
import storage.generic.GenericStorage;

/**
 *
 * @author Rastko
 */
public class UserServiceImpl implements UserService {
    
    private GenericStorage storage;

    public UserServiceImpl() {
        storage=new GenericStorage();
    }

    @Override
    public List<User> getAll() throws SQLException {
        return (List<User>)(List<?>)storage.getAll(new User());
    }

    @Override
    public boolean add(User user) throws SQLException {
        return storage.add(user);
    }
}
